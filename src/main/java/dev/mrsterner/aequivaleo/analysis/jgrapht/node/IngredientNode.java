package dev.mrsterner.aequivaleo.analysis.jgrapht.node;

import dev.mrsterner.aequivaleo.analysis.jgrapht.aequivaleo.IRecipeInputNode;
import dev.mrsterner.aequivaleo.analysis.jgrapht.aequivaleo.IStartAnalysisNode;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.Set;

public class IngredientNode extends AbstractNode implements IRecipeInputNode, IStartAnalysisNode
{
	@NotNull
	private final IRecipeIngredient ingredient;
	private final int hashCode;

	public IngredientNode(@NotNull final IRecipeIngredient ingredient) {
		this.ingredient = ingredient;
		this.hashCode = ingredient.hashCode();
	}

	@NotNull
	public IRecipeIngredient getIngredient()
	{
		return ingredient;
	}

	@Override
	public boolean equals(final Object o)
	{
		if (this == o)
		{
			return true;
		}
		if (!(o instanceof IngredientNode))
		{
			return false;
		}

		final IngredientNode that = (IngredientNode) o;

		return getIngredient().equals(that.getIngredient());
	}

	@Override
	public int hashCode()
	{
		return hashCode;
	}

	@Override
	public String toString()
	{
		return "IngredientNode{" + ingredient +
				'}';
	}

	@Override
	public void collectStats(final StatCollector statCollector)
	{
		statCollector.onVisitIngredientNode();
	}

	@Override
	public Set<CompoundInstance> getInputInstances(final IRecipeNode recipeNode)
	{
		return getResultingValue().orElse(Collections.emptySet());
	}

}
