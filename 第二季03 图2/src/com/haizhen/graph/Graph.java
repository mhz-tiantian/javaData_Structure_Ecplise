package com.haizhen.graph;

import java.util.List;

/**
 * 图的 类
 * 
 * @author mahaizhen
 *
 * @date 2020年8月27日
 * 
 *       V 表示的是顶点的元素 E 表示的是 权重
 */
public abstract class Graph<V, E> {

	WeightManager<E> weightManager;

	public Graph() {
	}

	public Graph(WeightManager<E> weightManager) {
		this.weightManager = weightManager;
	}

	/**
	 * 
	 * @return 返回边的数量
	 */
	public abstract int edgesSize();

	/**
	 * 
	 * @return 顶点的数量
	 */
	public abstract int verticesSize();

	/**
	 * 添加顶点
	 * 
	 * @param v
	 */
	public abstract void addVertex(V v);

	/**
	 * 删除那个顶点
	 * 
	 * @param v
	 */
	public abstract void removeVertex(V v);

	/**
	 * 添加边 没有权重
	 * 
	 * @param from 从哪个顶点
	 * @param to   到那个顶点
	 */
	public abstract void addEdge(V from, V to);

	/**
	 * 添加边,
	 * 
	 * @param from   从哪个顶点
	 * @param to     到那个顶点
	 * @param weight 权重
	 */
	public abstract void addEdge(V from, V to, E weight);

	/**
	 * 删除边
	 * 
	 * @param from
	 * @param to
	 */
	public abstract void removeEdge(V from, V to);

	// 广度优先搜索
	public abstract void bfs(V begin, VertexVisitor<V> visitor); // 广度优先搜索

	// 深度优先搜索
	public abstract void dfs(V begin, VertexVisitor<V> visitor);
	
	
	// 拓扑 排序
	public abstract List<V> topologicalSort();

	public interface WeightManager<E> { // 管理权重
		int compare(E w1, E w2); // 比较权重

		E add(E w1, E w2); // 权重相加

		E zero();
	}

	// 搜索的对外的接口
	public interface VertexVisitor<V> {
		boolean visit(V v);
	}

	/**
	 * 边的信息
	 * 
	 * @author mahaizhen
	 *
	 * @date 2020年8月31日
	 */
	public static class EdgeInfo<V, E> {

		private V from;
		private V to;
		private E weight;

		public EdgeInfo(V from, V to, E weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		public V getFrom() {
			return from;
		}

		public void setFrom(V from) {
			this.from = from;
		}

		public V getTo() {
			return to;
		}

		public void setTo(V to) {
			this.to = to;
		}

		public E getWeight() {
			return weight;
		}

		public void setWeight(E weight) {
			this.weight = weight;
		}

	}

}
