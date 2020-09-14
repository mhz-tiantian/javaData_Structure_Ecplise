package com.haizhen.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
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

	public void print() {
		vertices.forEach((V v, Vertex<V, E> vertex) -> {
			System.out.println(v);
		});

		edges.forEach((edge -> {
			System.out.println(edge);
		}));
	}

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

	/**
	 * 添加边的信息
	 */
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
		// 如果有的话 , 就把权值更新下, 没有的话,就把新创建一个边, 然后添加到 hashSize里面
		Edge<V, E> edge = new Edge<>(fromVertex, toVertex);
		edge.weight = weight;
		if (fromVertex.outEdges.remove(edge)) {
			toVertex.inEdges.remove(edge);
			edges.remove(edge);
		}
		fromVertex.outEdges.add(edge);
		toVertex.inEdges.add(edge);
		edges.add(edge);

	}

	// 删除顶点

	@Override
	public void removeVertex(V v) {
		Vertex<V, E> vertex = vertices.get(v);
		

	}

	// 删除边
	@Override
	public void removeEdge(V from, V to) {
		Vertex<V, E> fromVertex = vertices.get(from);
		if (fromVertex == null) {
			return;
		}
		Vertex<V, E> toVertex = vertices.get(to);
		if (toVertex == null) {
			return;
		}
		// 根据起点还有终点,获取边, 然后删除
		Edge<V, E> edge = new Edge<>(fromVertex, toVertex);
		if (fromVertex.outEdges.remove(edge)) {
			toVertex.inEdges.remove(edge);
			edges.remove(edge);
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

		@Override
		public boolean equals(Object obj) {
			return Objects.equals(value, ((Vertex<V, E>) (obj)).value);
		}

		@Override
		public int hashCode() {
			return value == null ? 0 : value.hashCode();
		}

		@Override
		public String toString() {
			return "Vertex [value=" + value + "]";
		}

	}

	/**
	 * 边的信息
	 * 
	 * @author mahaizhen
	 *
	 * @date 2020年9月14日
	 */
	private static class Edge<V, E> {
		Vertex<V, E> from; // 从那个顶点
		Vertex<V, E> to; // 到那个顶点
		E weight; // 权重

		Edge(Vertex<V, E> from, Vertex<V, E> to) {
			this.from = from;
			this.to = to;
		}

		@Override
		public boolean equals(Object obj) {
			Edge<V, E> edge = (Edge<V, E>) obj;
			return Objects.equals(from, edge.from) && Objects.equals(to, edge.to);
		}

		@Override
		public int hashCode() {
			int fromHashCode = from.hashCode();
			int toHashCode = to.hashCode();
			return fromHashCode * 31 + toHashCode;
		}

		@Override
		public String toString() {
			return "Edge [from=" + from.value + ", to=" + to.value + ", weight=" + weight + "]";
		}

	}

}
