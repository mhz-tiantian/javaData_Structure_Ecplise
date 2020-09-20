package com.haizhen.graph;

import com.haizhen.graph.Graph.WeightManager;

public class MainGraph {
	
	static WeightManager<Double> weightManager = new WeightManager<Double>() {
		public int compare(Double w1, Double w2) {
			return w1.compareTo(w2);
		}

		public Double add(Double w1, Double w2) {
			return w1 + w2;
		}

		@Override
		public Double zero() {
			return 0.0;
		}
	};

	public static void main(String[] args) {

//		test();
		testBfs();
	}
	
	static void testBfs() {
		Graph<Object, Double> graph = directedGraph(Data.BFS_02);
		graph.bfs(0, (Object v) -> {
			System.out.println(v);
			return false;
		});
	}
	
//	static void testDfs() {
//		Graph<Object, Double> graph = directedGraph(Data.DFS_02);
//		graph.dfs("a", (Object v) -> {
//			System.out.println(v);
//			return false;
//		});
//	}
	
	
	static void test() {
		ListGraph<String, Integer> graph = new ListGraph<>();
//		graph.addEdge("V0", "V1");
//		graph.addEdge("V1", "V0");
//		
//		graph.addEdge("V0", "V2");
//		graph.addEdge("V2", "V0");
//
//		graph.addEdge("V0", "V3");
//		graph.addEdge("V3", "V0");
//
//		graph.addEdge("V1", "V2");
//		graph.addEdge("V2", "V1");
//		
//		graph.addEdge("V2", "V3");
//		graph.addEdge("V3", "V2");
//		
//		graph.print();
		
		graph.addEdge("V1", "V0", 9);
		graph.addEdge("V1", "V2", 3);
		graph.addEdge("V2", "V0", 2);
		graph.addEdge("V2", "V3", 5);
		graph.addEdge("V3", "V4", 1);
		graph.addEdge("V0", "V4", 6);
		
		graph.print();
		
		graph.removeEdge("V0", "V4");
		graph.removeVertex("V0");
		
		graph.print();
	}
	
	
	/**
	 * 有向图
	 */
	private static Graph<Object, Double> directedGraph(Object[][] data) {
		Graph<Object, Double> graph = new ListGraph<>(weightManager);
		for (Object[] edge : data) {
			if (edge.length == 1) {
				graph.addVertex(edge[0]);
			} else if (edge.length == 2) {
				graph.addEdge(edge[0], edge[1]);
			} else if (edge.length == 3) {
				double weight = Double.parseDouble(edge[2].toString());
				graph.addEdge(edge[0], edge[1], weight);
			}
		}
		return graph;
	}
	
	/**
	 * 无向图
	 * @param data
	 * @return
	 */
	private static Graph<Object, Double> undirectedGraph(Object[][] data) {
		Graph<Object, Double> graph = new ListGraph<>(weightManager);
		for (Object[] edge : data) {
			if (edge.length == 1) {
				graph.addVertex(edge[0]);
			} else if (edge.length == 2) {
				graph.addEdge(edge[0], edge[1]);
				graph.addEdge(edge[1], edge[0]);
			} else if (edge.length == 3) {
				double weight = Double.parseDouble(edge[2].toString());
				graph.addEdge(edge[0], edge[1], weight);
				graph.addEdge(edge[1], edge[0], weight);
			}
		}
		return graph;
	}

}
