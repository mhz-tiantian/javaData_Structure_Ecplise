package com.haizhen.union;

/**
 * Quick union 基于Rank 的优化 路径压缩(path Compression) 什么是路径压缩: 在find 的时候使路径上的所有节点, 都指向根节点,
 * 从而降低树的高度
 * 
 * @author mahaizhen
 *
 * @date 2020年8月11日
 */
public class UnionFind_QU_RANK_PC extends UnionFind_QU_RANK {

	public UnionFind_QU_RANK_PC(int capacity) {
		super(capacity);
	}

	@Override
	public int find(int v) {
		rangeCheck(v);
		if (parents[v] != v) {
			//修改v的根节点
			parents[v] = find(parents[v]);
		}
		return parents[v];
	}
}
