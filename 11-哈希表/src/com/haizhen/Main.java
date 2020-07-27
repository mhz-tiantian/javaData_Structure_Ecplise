package com.haizhen;

import com.haizhen.map.HashMap;
import com.haizhen.map.Map;
import com.haizhen.map.Map.Visitor;
import com.haizhen.model.Key;
import com.haizhen.map.TreeMap;

public class Main {

	static void test1() {
		Map<String, Integer> map = new HashMap();
		map.put("c", 2);
		map.put("a", 5);
		map.put("b", 6);
		map.put("a", 8);

		map.traversal(new Visitor<String, Integer>() {
			public boolean visit(String key, Integer value) {
				System.out.println(key + "_" + value);
				return false;
			}
		});
	}

	static void test2() {
		HashMap<Object, Integer> map = new HashMap();
		for (int i = 1; i <= 20; i++) {
			map.put(new Key(i), i);
		}
//		System.out.println(map.size());
//		System.out.println(map.get(new Key(1)));
		map.print();

	}

	public static void main(String[] args) {
		test2();
	}

}
