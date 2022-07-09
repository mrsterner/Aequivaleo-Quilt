package dev.mrsterner.aequivaleo.apiimpl;

import com.google.gson.GsonBuilder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.registries.IForgeRegistry;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.function.Function;

public final class AequivaleoAPI implements IAequivaleoAPI
{
	private static final AequivaleoAPI ourInstance = new AequivaleoAPI();

	public static AequivaleoAPI getInstance()
	{
		return ourInstance;
	}

	private AequivaleoAPI()
	{
	}

	@Override
	public ICompoundContainerFactoryManager getCompoundContainerFactoryManager()
	{
		return CompoundContainerFactoryManager.getInstance();
	}

	@Override
	public IGameObjectEquivalencyHandlerRegistry getGameObjectEquivalencyHandlerRegistry()
	{
		return GameObjectEquivalencyHandlerRegistry.getInstance();
	}

	@Override
	public IEquivalencyRecipeRegistry getEquivalencyRecipeRegistry(@NotNull final ResourceKey<Level> worldKey)
	{
		return EquivalencyRecipeRegistry.getInstance(worldKey);
	}

	@Override
	public ICompoundInformationRegistry getLockedCompoundWrapperToTypeRegistry(@NotNull final ResourceKey<Level> worldKey)
	{
		return CompoundInformationRegistry.getInstance(worldKey);
	}

	@Deprecated
	@Override
	public IResultsInformationCache getResultsInformationCache(@NotNull final ResourceKey<Level> worldKey)
	{
		return EquivalencyResults.getInstance(worldKey);
	}

	@Override
	public IEquivalencyResults getEquivalencyResults(final ResourceKey<Level> worldKey)
	{
		return EquivalencyResults.getInstance(worldKey);
	}

	@Override
	public IAequivaleoPluginManager getPluginManager()
	{
		return PluginManger.getInstance();
	}

	@Override
	public IRecipeCalculator getRecipeCalculator()
	{
		return RecipeCalculator.getInstance();
	}

	@Override
	public GsonBuilder setupGson(final GsonBuilder builder, final ICondition.IContext context)
	{
		return builder
				.setLenient()
				.registerTypeAdapter(CompoundInstanceDataModeSerializer.HANDLED_TYPE, new CompoundInstanceDataModeSerializer())
				.registerTypeAdapter(CompoundInstanceDataSerializer.HANDLED_TYPE, new CompoundInstanceDataSerializer(context))
				.registerTypeAdapter(CompoundInstanceRefSerializer.HANDLED_TYPE, new CompoundInstanceRefSerializer())
				.registerTypeAdapter(CompoundInstanceRefSetSerializer.HANDLED_TYPE, new CompoundInstanceRefSetSerializer())
				.registerTypeAdapter(CompoundContainerSetSerializer.HANDLED_TYPE, new CompoundContainerSetSerializer())
				.registerTypeAdapter(CompoundContainerFactoryManager.HANDLED_TYPE, CompoundContainerFactoryManager.getInstance())
				.registerTypeAdapter(IngredientSerializerRegistry.HANDLED_TYPE, IngredientSerializerRegistry.getInstance())
				.registerTypeAdapter(IngredientSetSerializer.HANDLED_TYPE, new IngredientSetSerializer())
				.registerTypeAdapter(GenericRecipeDataSerializer.HANDLED_TYPE, new GenericRecipeDataSerializer(context))
				.registerTypeAdapter(ResourceLocation.class, new ResourceLocation.Serializer());
	}

	@Override
	public IRecipeTypeProcessingRegistry getRecipeTypeProcessingRegistry()
	{
		return RecipeTypeProcessingRegistry.getInstance();
	}

	@Override
	public IInstancedEquivalencyHandlerRegistry getInstancedEquivalencyHandlerRegistry()
	{
		return InstancedEquivalencyHandlerRegistry.getInstance();
	}

	@Override
	public IResultsAdapterHandlerRegistry getResultsAdapterHandlerRegistry()
	{
		return ResultsAdapterHandlerRegistry.getInstance();
	}

	@Override
	public IIngredientSerializerRegistry getIngredientSerializerRegistry()
	{
		return IngredientSerializerRegistry.getInstance();
	}

	@Override
	public AnalysisState getState(final ResourceKey<Level> key)
	{
		return AnalysisStateManager.getState(key);
	}

	@Override
	public IBlacklistDimensionManager getBlacklistDimensionManager()
	{
		return BlacklistDimensionManager.getInstance();
	}

	@Override
	public <T extends IRegistryEntry, E extends IRegistryEntry> IRegistryView<E> createView(final IForgeRegistry<T> registry, final Function<T, Optional<E>> viewFilter) {
		return new ShadowRegistry<>(registry, viewFilter);
	}
}
Footer
