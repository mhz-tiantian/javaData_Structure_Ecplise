package com.haizhen;

import com.haizhen.set.LinkedSet;
import com.haizhen.set.Set;
import com.haizhen.set.Set.Visitor;
import com.haizhen.set.TreeSet;

public class Main {

	public static void main(String[] args) {

//		Set<Integer> linkedSet = new LinkedSet<>();
//		linkedSet.add(10);
//		linkedSet.add(11);
//		linkedSet.add(11);
//		linkedSet.add(12);
//		linkedSet.add(10);
		
		
		Set<Integer> treeSet = new TreeSet<>();
		treeSet.add(12);
		treeSet.add(10);
		treeSet.add(11);
		treeSet.add(11);
		treeSet.add(10);
		treeSet.traversal(new Visitor<Integer>() {

			@Override
			public boolean visit(Integer element) {
				System.out.println(element);
				return false;
			}
		});

	}

}
