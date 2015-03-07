package model;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.jgrapht.EdgeFactory;
import org.jgrapht.UndirectedGraph;


public class Graph implements UndirectedGraph<Node, Edge> {

	private HashSet<Node> nodes;
	private HashSet<Edge> edges;
	private HashMap<String, Node> mapNameNode;
	
	public Graph() {
		this.nodes = new HashSet<Node>();
		this.edges = new HashSet<Edge>();
		this.mapNameNode = new HashMap();
	}
		
	public HashSet<Node> getNodes() {
		return nodes;
	}

	public void setNodes(HashSet<Node> nodes) {
		this.nodes = nodes;
	}

	public HashSet<Edge> getEdges() {
		return edges;
	}

	public void setEdges(HashSet<Edge> edges) {
		this.edges = edges;
	}
	
	public HashMap<String, Node> getMapNameNode() {
		return this.mapNameNode;
	}

	@Override
	public Edge addEdge(Node n1, Node n2) {
		//n1.addEdge(n2);
		Edge ed = new Edge(n1, n2);
		//n2.addEdge(n1);
		this.edges.add(ed);
		return ed;
	}
	
	
	public void addInderectEdge(Node n1, Node n2) {
		/*n1.addEdge(n2);
		n2.addEdge(n1);*/
		this.edges.add(new Edge(n1, n2));
		this.edges.add(new Edge(n2, n1));
	}
	
	@Override
	public boolean addEdge(Node n1, Node n2, Edge edge) {
		// TODO Auto-generated method stub
		/*n1.addEdge(n2);
		n2.addEdge(n1);*/
		return this.edges.add(edge);
	}

	@Override
	public boolean addVertex(Node n) {
		// TODO Auto-generated method stub
		this.mapNameNode.put(n.getName(), n);
		return this.nodes.add(n);
		//return true;
	}
	
	public boolean addVertex(String name) {
		Node nn = new Node(name);
		this.nodes.add(nn);
		this.mapNameNode.put(name, nn);
		return true;
	}
	
	public boolean containNodeByName(String name) {
		return this.nodes.contains(this.mapNameNode.get(name));
	}
	
	public boolean containEdgeByNames(String name1, String name2) {
		Node n1 = this.mapNameNode.get(name1);
		Node n2 = this.mapNameNode.get(name2);
		return this.containsEdge(n1, n2);
		
	}
	
	/*public Node getNodeByName(String name) {
		
	}*/

	@Override
	public boolean containsEdge(Edge arg0) {
		// TODO Auto-generated method stub
		return this.edges.contains(arg0);
	}

	@Override
	public boolean containsEdge(Node n1, Node n2) {
		// TODO Auto-geerated method stub
		for(Edge e : this.edges) {
			if (e.getFrom().equals(n1) && e.getTo().equals(n2))
				return true;
		}
		return false;
	}

	@Override
	public boolean containsVertex(Node n1) {
		// TODO Auto-generated method stub
		return this.nodes.contains(n1);
	}

	@Override
	public Set<Edge> edgeSet() {
		// TODO Auto-generated method stub
		return this.edges;
	}

	@Override
	public Set<Edge> edgesOf(Node n1) {
		// TODO Auto-generated method stub
		Set<Edge> edges = new HashSet<Edge>();
		for(Edge e : this.edges) {
			if (e.getFrom().equals(n1) || e.getTo().equals(n1))
				edges.add(e);
		}
		/*edges.addAll(n1.getInEdges());
		edges.addAll(n1.getOutEdges());*/
		return edges;
	}

	@Override
	public Set<Edge> getAllEdges(Node n1, Node n2) {
		HashSet<Edge> allEdge = new HashSet<Edge>();
		for(Edge e : this.edges) {
			if ((e.getFrom().equals(n1) && e.getTo().equals(n2)) ||
				(e.getFrom().equals(n2) && e.getTo().equals(n1)))
				allEdge.add(e);
		}
		return allEdge;
	}

	@Override
	public Edge getEdge(Node n1, Node n2) {
		// TODO Auto-generated method stub
		for(Edge e : this.edges) {
			if (e.getFrom().equals(n1) && e.getTo().equals(n2))
				return e;
		}
		return null;
	}

	@Override
	public EdgeFactory<Node, Edge> getEdgeFactory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node getEdgeSource(Edge arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node getEdgeTarget(Edge arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getEdgeWeight(Edge arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean removeAllEdges(Collection<? extends Edge> arg0) {
		// TODO Auto-generated method stub
		return this.edges.removeAll(arg0);
	}

	@Override
	public Set<Edge> removeAllEdges(Node n1, Node n2) {
		// TODO Auto-generated method stub
		HashSet<Edge> allEdge = new HashSet<Edge>();
		for(Edge e : this.edges) {
			if ((e.getFrom().equals(n1) && e.getTo().equals(n2)) ||
				(e.getFrom().equals(n2) && e.getTo().equals(n1))) {
				allEdge.add(e);
				this.edges.remove(e);
			}
		}
		return allEdge;
	}

	@Override
	public boolean removeAllVertices(Collection<? extends Node> arg0) {
		// TODO Auto-generated method stub
		return this.nodes.removeAll(arg0);
	}

	@Override
	public boolean removeEdge(Edge arg0) {
		// TODO Auto-generated method stub
		return this.edges.remove(arg0);
	}

	@Override
	public Edge removeEdge(Node n1, Node n2) {
		// TODO Auto-generated method stub
		Edge ed = this.getEdge(n1, n2);
		this.edges.remove(ed);
		return ed;
	}

	@Override
	public boolean removeVertex(Node arg0) {
		// TODO Auto-generated method stub
		return this.nodes.remove(arg0);
	}

	@Override
	public Set<Node> vertexSet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int degreeOf(Node n1) {
		return this.edgesOf(n1).size();
	}

}
