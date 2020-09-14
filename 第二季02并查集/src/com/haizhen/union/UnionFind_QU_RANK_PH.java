package com.haizhen.union;

/**
 * Quick union 基于Rank 的优化 Rank PH (Path Halving) 路径减半
 * 
 * 
 * 路径减半:使路径上每隔一个节点就指向其祖父节点(parent 的parent)
 * 
 * @author mahaizhen
 *
 * @date 2020年8月11日
 */
public class UnionFind_QU_RANK_PH extends UnionFind_QU_RANK {

	public UnionFind_QU_RANK_PH(int capacity) {
		super(capacity);
	}

	@Override
	public int find(int v) {
		rangeCheck(v);
		while (v != parents[v]) {
			// 把 v的父节点, 改变成祖父节点(parent 的parent)
			parents[v] = parents[parents[v]];
			// 把新的父节点  复制给v 开始下次循环
			v = parents[v];
		}
		return v;
	}

}
