package com.haizhen.union;

/**
 * quick find
 * 
 * @author mahaizhen
 *
 * @date 2020年8月10日
 */
public class UnionFind_QF extends UnionFind {

	public UnionFind_QF(int capacity) {
		super(capacity);
	}

	@Override
	public int find(int v) {
		rangeCheck(v);
		return parents[v];
	}

	/**
	 * 把v1集合的全部节点的父节点, 都修改成 p2
	 */
	@Override
	public void union(int v1, int v2) {
		int p1 = find(v1);
		int p2 = find(v2);

		for (int i = 0; i < parents.length; i++) {
			if (parents[i] == p1) {
				parents[i] = p2;
			}

		}

	}

}
