package test;

import model.RelationshipGraph;

public class testGraph {
	public static void main (String[] args) {
		RelationshipGraph rg = new RelationshipGraph();
		System.out.println(rg.getGraph().toString());
	}
}
