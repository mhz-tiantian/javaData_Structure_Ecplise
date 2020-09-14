package com.haizhen.union;

/**
 * Quick union 以size为准 进行树层级减少的操作
 * 
 * @author mahaizhen
 *
 * @date 2020年8月10日
 */
public class UnionFind_QU_SIZE extends UnionFind_QU {
	private int[] sizes;

	public UnionFind_QU_SIZE(int capacity) {
		super(capacity);
		sizes = new int[capacity];
		// 初始化默认的size
		for (int i = 0; i < sizes.length; i++) {
			sizes[i] = 1;
		}
	}

	@Override
	public void union(int v1, int v2) {
		// p1 p2 为根节点
		int p1 = find(v1);
		int p2 = find(v2);
		if (p1 == p2) {
			return;
		}
		if (sizes[p1] < sizes[p2]) {
			// 把p1的根节点, 修改成p2 
			parents[p1] = p2;
			// p2的树的节点数量  就等于 p2树之前的数量, 加上p1树的全部数量
			sizes[p2] += sizes[p1];
		} else {
			// 把p2 的根节点, 修改成p1 
			parents[p2] = p1;
			// p1的树的节点数量  就等于 p1树之前的数量, 加上p2树的全部数量
			sizes[p1] += sizes[p2];
		}

	}

}
