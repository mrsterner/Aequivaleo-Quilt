package dev.mrsterner.aequivaleo;

import dev.mrsterner.aequivaleo.apiimpl.AequivaleoAPI;
import dev.mrsterner.aequivaleo.bootstrap.CompoundContainerFactoryRegistrar;
import dev.mrsterner.aequivaleo.bootstrap.CompoundTypesRegistrar;
import dev.mrsterner.aequivaleo.config.Configuration;
import dev.mrsterner.aequivaleo.network.NetworkChannel;
import dev.mrsterner.aequivaleo.recipe.equivalency.RecipeCalculatorLogHandler;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Aequivaleo implements ModInitializer {
	private static Aequivaleo INSTANCE;
	private static final Logger LOGGER = LogManager.getLogger();

	private final Configuration configuration;
	private final NetworkChannel networkChannel;


	@Override
	public void onInitialize(ModContainer mod) {
		LOGGER.info("Aequivaleo is being instantiated.");

		RecipeCalculatorLogHandler.setupLogging();

		CompoundContainerFactoryRegistrar.COMPOUND_CONTAINER_FACTORY_REGISTRY.register(FMLJavaModLoadingContext.get().getModEventBus());
		CompoundTypesRegistrar.COMPOUND_TYPE_GROUP_REGISTRY.register(FMLJavaModLoadingContext.get().getModEventBus());
		CompoundTypesRegistrar.COMPOUND_TYPE_REGISTRY.register(FMLJavaModLoadingContext.get().getModEventBus());

		INSTANCE = this;
		IAequivaleoAPI.Holder.setInstance(IAequivaleoAPI.getInstance());
		StreamUtils.setup(IAequivaleoAPI.getInstance());

		configuration = new Configuration(ModLoadingContext.get().getActiveContainer());
		networkChannel = new NetworkChannel(Constants.MOD_ID);

		PluginManger.getInstance().detect();
		PluginManger.getInstance().run(IAequivaleoPlugin::onConstruction);

		Mod.EventBusSubscriber.Bus.MOD.bus().get().addListener(AnalysisLogHandler::onConfigurationReloaded);
	}

	public static Aequivaleo getInstance()
	{
		return INSTANCE;
	}

	public Configuration getConfiguration()
	{
		return configuration;
	}

	public NetworkChannel getNetworkChannel()
	{
		return networkChannel;
	}
}
}
