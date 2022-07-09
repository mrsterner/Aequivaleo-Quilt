package dev.mrsterner.aequivaleo.analysis.jgrapht;


import dev.mrsterner.aequivaleo.analysis.jgrapht.aequivaleo.IGraph;
import dev.mrsterner.aequivaleo.analysis.jgrapht.aequivaleo.INode;
import dev.mrsterner.aequivaleo.analysis.jgrapht.node.SourceNode;

import java.util.Map;
import java.util.Set;

public class BuildRecipeGraph
{

	private final IGraph recipeGraph;
	private final Map<ICompoundContainer<?>, Set<CompoundInstance>> resultingCompounds;
	private final Map<ICompoundContainer<?>, INode>  compoundNodes;
	private final Map<IRecipeIngredient, INode> ingredientNodes;
	private final Set<INode> notDefinedGraphNodes;
	private final SourceNode sourceNode;

	public BuildRecipeGraph(
			final IGraph recipeGraph,
			final Map<ICompoundContainer<?>, Set<CompoundInstance>> resultingCompounds,
			final Map<ICompoundContainer<?>, INode> compoundNodes,
			final Map<IRecipeIngredient, INode> ingredientNodes,
			final Set<INode> notDefinedGraphNodes,
			final SourceNode sourceNode)
	{
		this.recipeGraph = recipeGraph;
		this.resultingCompounds = resultingCompounds;
		this.compoundNodes = compoundNodes;
		this.ingredientNodes = ingredientNodes;
		this.notDefinedGraphNodes = notDefinedGraphNodes;
		this.sourceNode = sourceNode;
	}

	public IGraph getRecipeGraph()
	{
		return recipeGraph;
	}

	public Map<ICompoundContainer<?>, Set<CompoundInstance>> getResultingCompounds()
	{
		return resultingCompounds;
	}

	public Map<ICompoundContainer<?>, INode> getCompoundNodes()
	{
		return compoundNodes;
	}

	public Map<IRecipeIngredient, INode> getIngredientNodes()
	{
		return ingredientNodes;
	}

	public Set<INode> getNotDefinedGraphNodes()
	{
		return notDefinedGraphNodes;
	}

	public SourceNode getSourceNode()
	{
		return sourceNode;
	}
}
