package test;

import java.util.Collection;
import java.util.Set;

import model.CliqueMaker;
import model.Node;
import model.RelationshipGraph;
import model.UndirectGraph;
import util.FileManager;
import util.FileManagerInterface;
import util.Object2JSON;

public class testClique {

	public static void main(String[] args) {
		CliqueMaker cc= new CliqueMaker();
		RelationshipGraph rg = new RelationshipGraph();
		UndirectGraph gh =rg.getGraph();
		Collection<Set<Node>> nodes = cc.makeCliques(gh);
		Object2JSON obj = new Object2JSON();
		String js = obj.serializeObject(nodes);
		FileManagerInterface ff = new FileManager();
		ff.createFile("./src/file/cliqueMaximal.txt", js);
		System.out.println(js);

	}

}
