package test;

import util.FileManager;
import util.FileManagerInterface;
import util.Object2JSON;
import model.Graph;
import model.RelationshipGraph;
import model.UndirectGraph;


public class testGraph {
	public static void main (String[] args) {
		RelationshipGraph rg = new RelationshipGraph();
		UndirectGraph gh =rg.getGraph();
		Object2JSON obj = new Object2JSON();
		String js = obj.serializeObject(gh);
		FileManagerInterface ff = new FileManager();
		ff.createFile("./src/file/graph.txt", js);
		System.out.println(js);
	}
}
