package com.haizhen.union;

/**
 * 
 * 
 * Quick union 基于Rank 的优化 Rank PS (Path Spliting) 路径分裂 路径分裂
 * 使路径上的每个节点都指向其祖父节点(parent的parent)
 * 
 * @author mahaizhen
 *
 * @date 2020年8月11日
 */
public class UnionFind_QU_RANK_PS extends UnionFind_QU_RANK {

	public UnionFind_QU_RANK_PS(int capacity) {
		super(capacity);
	}

	@Override
	public int find(int v) {
		rangeCheck(v);
		while (v != parents[v]) {
			int p = parents[v];
			// 把 v的父节点, 改变成祖父节点(parent 的parent)
			parents[v] = parents[parents[v]];
			// 把 p 复制给 v 开始下一次循环
			v = p;
		}
		return parents[v];
	}

}
