package com.haizhen.union;

/**
 * 并查集
 * 
 * @author mahaizhen
 *
 * @date 2020年8月10日
 */
public abstract class UnionFind {

	protected int[] parents;

	public UnionFind(int capacity) {
		if (capacity < 1) {
			throw new IllegalArgumentException(" capacity  must be >=1");
		}
		parents = new int[capacity];
		for (int i = 0; i < parents.length; i++) {
			parents[i] = i;
		}
	}

	/**
	 * 查找 v所属的根节点
	 * 
	 * 查找父节点, 父节点的值, 应该是数组对应的下标是一致的, 不一致的话, 其实就不是父节点
	 * @param v
	 * @return
	 */
	public abstract int find(int v);

	/**
	 * 合并 v1 v2 所在的集合
	 * 
	 * @param v1
	 * @param v2
	 */
	public abstract void union(int v1, int v2);

	/**
	 * 检查v1 和v2 是不是在同一个集合
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public boolean isSame(int v1, int v2) {
		return find(v1) == find(v2);
	}

	protected void rangeCheck(int v) {
		if (v < 0 || v >= parents.length) {
			throw new IllegalArgumentException("v is out of bounds");
		}
	}

	public void printArray() {
		for (int i = 0; i < parents.length; i++) {
			System.out.print(parents[i] + "\t");
		}
	}

}
