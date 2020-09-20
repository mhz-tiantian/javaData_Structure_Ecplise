package com.haizhen.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;

public class ListGraph<V, E> extends Graph<V, E> {
	/**
	 * 边的 数量
	 */

	public ListGraph() {

	}

	public ListGraph(WeightManager<E> weightManager) {
		super(weightManager);
	}

	// 传入的V 与顶点类Vertex的映射
	private Map<V, Vertex<V, E>> vertices = new HashMap<>();

	// 所有边的 的集合
	private Set<Edge<V, E>> edges = new HashSet<>();

	public void print() {
		System.out.println("[顶点]-------------------");
		vertices.forEach((V v, Vertex<V, E> vertex) -> {
			System.out.println(v);
			System.out.println("out-----------");
			System.out.println(vertex.outEdges);
			System.out.println("int-----------");
			System.out.println(vertex.inEdges);
		});
		System.out.println("[边]-------------------");
		edges.forEach((Edge<V, E> edge) -> {
			System.out.println(edge);
		});
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
		// 删除顶点
		Vertex<V, E> vertex = vertices.remove(v);
		if (vertex == null) {
			// 说明删除的顶点不存在
			return;
		}
		// 说明删除的顶点存在, 然后先去删除 从这个顶点出去的边
		Iterator<Edge<V, E>> outIterator = vertex.outEdges.iterator();
		while (outIterator.hasNext()) {
			Edge<V, E> outEdge = outIterator.next();
			// 获取outEdge 边的终点 从中删除遍历到的边
			outEdge.to.inEdges.remove(outEdge);
			outIterator.remove();
			edges.remove(outEdge);
		}

		// 说明顶点存在, 删除所有进入这个顶点的边
		Iterator<Edge<V, E>> inIterator = vertex.inEdges.iterator();
		while (inIterator.hasNext()) {
			Edge<V, E> edge = inIterator.next();
			edge.from.outEdges.remove(edge);
			inIterator.remove();
			edges.remove(edge);
		}

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

	// 广度优先搜索
	@Override
	public void bfs(V begin, VertexVisitor<V> visitor) {
		// 如果传入的搜索的内容是null的话, 直接返回
		if (visitor == null) {
			return;
		}
		// 通过V begin 来拿到顶点的信息
		Vertex<V, E> beginVertex = vertices.get(begin);
		// 拿到的顶点信息为空, 直接返回了 , 不做处理
		if (beginVertex == null) {
			return;
		}
		// 已经选择过的顶点, 如果是已经选择过的 , 就直接去遍历下一个了
		Set<Vertex<V, E>> selectedVertexs = new HashSet<>();
		Queue<Vertex<V, E>> queue = new LinkedList<>();
		// 先把从哪里开始遍历的顶点信息放入队列里面
		queue.offer(beginVertex);
		selectedVertexs.add(beginVertex);
		while (!queue.isEmpty()) {
			// 拿到顶点信息
			Vertex<V, E> vertex = queue.poll();
			// 这里要先进行打印出信息
			if (visitor.visit(vertex.value)) {
				// 如果返回true , 直接返回了, 不在进行 搜索了
				return;
			}

			// 遍历从这个顶点出去的边
			for (Edge<V, E> edge : vertex.outEdges) {
				if (selectedVertexs.contains(edge.to)) {
					// 如果有这个顶点了, 就直接去遍历下一个
					continue;
				}
				// 这里肯定是没有 这个顶点信息的
				queue.offer(edge.to);
				selectedVertexs.add(edge.to);
			}

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
