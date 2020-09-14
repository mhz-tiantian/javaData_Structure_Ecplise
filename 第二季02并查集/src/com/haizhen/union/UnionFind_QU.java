package com.haizhen.union;

/**
 * Quick union
 * 
 * @author mahaizhen
 *
 * @date 2020年8月10日
 */
public class UnionFind_QU extends UnionFind {

	public UnionFind_QU(int capacity) {
		super(capacity);
	}

	
	@Override
	public int find(int v) {
		rangeCheck(v);
		while (v != parents[v]) {
			v = parents[v];
		}
		return v;
	}

	@Override
	public void union(int v1, int v2) {
		int p1 = find(v1);
		int p2 = find(v2);
		if (p1 == p2) {
			return;
		}
		//把p1 的根节点修改成p2 
		parents[p1] = p2;

	}

}
