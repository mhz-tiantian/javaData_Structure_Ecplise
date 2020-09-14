package com.haizhen.graph;

public class MainGraph {

	public static void main(String[] args) {
		ListGraph<String, Integer> graph = new ListGraph<String, Integer>();
		graph.addEdge("V1", "V0", 9);
		graph.addEdge("V1", "V2", 3);
		graph.addEdge("V2", "V0", 2);
		graph.addEdge("V2", "V3", 5);
		graph.addEdge("V3", "V4", 1);
		graph.addEdge("V0", "V4", 6);
		graph.print();

	}

}
