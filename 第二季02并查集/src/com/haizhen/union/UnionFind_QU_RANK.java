package com.haizhen.union;

/**
 * Quick union   基于Rank 的优化   Rank
 * 
 * @author mahaizhen
 *
 * @date 2020年8月11日
 */
public class UnionFind_QU_RANK extends UnionFind_QU {

	private int[] ranks;

	public UnionFind_QU_RANK(int capacity) {
		super(capacity);
		ranks = new int[capacity];
		// 初始化树的层高
		for (int i = 0; i < parents.length; i++) {
			ranks[i] = 1;
		}
	}

	@Override
	public void union(int v1, int v2) {
		int p1 = find(v1);
		int p2 = find(v2);
		if (p1 == p2) {
			return;
		}
		// p1树的层高, 小于p2 树的层高
		if (ranks[p1] < ranks[p2]) {
			parents[p1] = p2;
		} else if (ranks[p1] > ranks[p2]) {
			parents[p2] = p1;
		} else {
			parents[p2] = p1;
			ranks[p1] += 1;
		}

	}

}
