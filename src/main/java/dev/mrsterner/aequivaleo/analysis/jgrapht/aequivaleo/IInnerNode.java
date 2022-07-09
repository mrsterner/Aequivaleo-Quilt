package dev.mrsterner.aequivaleo.analysis.jgrapht.aequivaleo;


import java.util.Set;

/**
 * Represents a graph node which has other nodes contained in itself.
 */
public interface IInnerNode extends INode, IAnalysisNodeWithSubNodes<IGraph, Set<CompoundInstance>, INode, IEdge>
{
}
