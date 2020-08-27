package com.haizhen;

import com.haizhen.map.Map;
import com.haizhen.map.Map.Visitor;
import com.haizhen.map.TreeMap;

public class Main {
	
	static void test1() {
		Map<String, Integer> map = new TreeMap<>();
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

	public static void main(String[] args) {
		test1();

	}

}
