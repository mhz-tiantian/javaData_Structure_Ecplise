package com.haizhen;

import com.haizhen.tools.Asserts;
import com.haizhen.tools.Times;
import com.haizhen.union.UnionFind;
import com.haizhen.union.UnionFind_QF;
import com.haizhen.union.UnionFind_QU;
import com.haizhen.union.UnionFind_QU_RANK;
import com.haizhen.union.UnionFind_QU_RANK_PC;
import com.haizhen.union.UnionFind_QU_RANK_PH;
import com.haizhen.union.UnionFind_QU_RANK_PS;
import com.haizhen.union.UnionFind_QU_SIZE;

public class Main {
	static final int count = 1000000;

	public static void main(String[] args) {
//		test(new UnionFind_QF(count));
//		test(new UnionFind_QU(count));// 太慢了 不在测试了
		test(new UnionFind_QU_SIZE(count));
		test(new UnionFind_QU_RANK(count));
		test(new UnionFind_QU_RANK_PC(count));
		test(new UnionFind_QU_RANK_PS(count));
		test(new UnionFind_QU_RANK_PH(count));
		
	}

	static void test(UnionFind uf) {
		uf.union(0, 1);
		uf.union(0, 3);
		uf.union(0, 4);
		uf.union(2, 3);
		uf.union(2, 5);
		
		uf.union(6, 7);
		uf.union(8, 10);
		uf.union(9, 10);
		uf.union(9, 11);
		Asserts.test(!uf.isSame(2, 7));
		uf.union(4, 6);
		Asserts.test(uf.isSame(2, 7));
		
		
		Times.test(uf.getClass().getSimpleName(), () -> {
			for (int i = 0; i < count; i++) {
				uf.union((int) (Math.random() * count), (int) (Math.random() * count));
			}

			for (int i = 0; i < count; i++) {
				uf.isSame((int) (Math.random() * count), (int) (Math.random() * count));
			}
		});
	}

}
