package com.haizhen.graph;

/**
 * 图的接口
 * 
 * @author Administrator V 表示的是顶点 Vertex E 表示的是权值 weight
 *
 */
public interface Graph<V, E> {

	/**
	 * 返回这个类顶点的数量
	 * 
	 * @return
	 */
	int vertexSize();

	/**
	 * 返回这个图边的数量
	 * 
	 * @return
	 */
	int edgeSize();

	/**
	 * 添加一个顶点
	 * 
	 * @param v
	 */
	void addVertex(V v);

	/**
	 * 添加一个边 from 从那个顶点 to 到那个顶点
	 */
	void addEdge(V from, V to);

	/**
	 * 添加一个边
	 * 
	 * @param from   从那个顶点
	 * @param to     到那个顶点
	 * @param weight 权值
	 */
	void addEdge(V from, V to, E weight);

	/**
	 * 删除一个顶点
	 * 
	 * @param v
	 */
	void removeVertex(V v);

	/**
	 * 删除一条边
	 * 
	 * @param from
	 * @param to
	 */
	void removeEdge(V from, V to);

}
