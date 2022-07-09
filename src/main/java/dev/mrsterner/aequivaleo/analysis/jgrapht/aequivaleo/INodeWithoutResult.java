package dev.mrsterner.aequivaleo.analysis.jgrapht.aequivaleo;

import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.Set;

public interface INodeWithoutResult extends INode
{

	@NotNull
	@Override
	default Optional<Set<CompoundInstance>> getResultingValue() {
		return Optional.empty();
	}

	@Override
	default boolean hasMissingData(IGraph graph, ICompoundTypeGroup group)
	{
		if (!graph.containsVertex(this))
		{
			return false;
		}
		for (IEdge iEdge : graph.incomingEdgesOf(this))
		{
			INode node = graph.getEdgeSource(iEdge);
			if (node.hasMissingData(graph, group))
			{
				return true;
			}
		}
		return false;
	}
}
