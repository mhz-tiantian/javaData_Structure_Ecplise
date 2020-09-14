package com.haizhen.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ListGraph<V, E> extends Graph<V, E> {
	/**
	 * 边的 数量
	 */

	public ListGraph() {
	}

	// 传入的V 与顶点类Vertex的映射
	private Map<V, Vertex<V, E>> vertices = new HashMap<>();

	// 所有边的 的集合
	private Set<Edge<V, E>> edges = new HashSet<>();

	@Override
	public int edgesSize() {
		return edges.size();
	}

	@Override
	public int verticesSize() {
		return vertices.size();
	}

	/**
	 * 添加一个顶点
	 */
	@Override
	public void addVertex(V v) {
		// 如果顶点已经存在了, 就不添加顶点
		if (vertices.containsKey(v)) {
			return;
		}
		vertices.put(v, new Vertex<V, E>(v));

	}

	@Override
	public void addEdge(V from, V to) {
		// 添加边的信息
		addEdge(from, to, null);

	}

	@Override
	public void addEdge(V from, V to, E weight) {
		// 根据传入的参数 from 找到起点, 如果不存在就创建
		Vertex<V, E> fromVertex = vertices.get(from);
		if (fromVertex == null) {
			fromVertex = new Vertex<>(from);
			vertices.put(from, fromVertex);
		}

		// 根据传入的参数to 找到终点, 如果不存在,就创建
		Vertex<V, E> toVertex = vertices.get(to);
		if (toVertex == null) {
			toVertex = new Vertex<>(to);
			vertices.put(to, toVertex);
		}
	}

	/**
	 * 订点的信息
	 * 
	 * @author mahaizhen
	 *
	 * @date 2020年8月31日
	 */
	private static class Vertex<V, E> {
		V value;
		// 进来的边
		Set<Edge<V, E>> inEdges = new HashSet<>();
		// 出去的边
		Set<Edge<V, E>> outEdges = new HashSet<>();

		Vertex(V value) {
			this.value = value;
		}

	}

	private static class Edge<V, E> {
		Vertex<V, E> from; // 从那个顶点
		Vertex<V, E> to; // 到那个顶点
		E weight; // 权重

		Edge(Vertex<V, E> from, Vertex<V, E> to) {
			this.from = from;
			this.to = to;
		}

	}

}
