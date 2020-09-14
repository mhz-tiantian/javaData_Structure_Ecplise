package com.haizhen.graph;

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
	 *  删除那个顶点
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
	 *  删除边
	 * @param from
	 * @param to
	 */
	public abstract void removeEdge(V from, V to);

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
