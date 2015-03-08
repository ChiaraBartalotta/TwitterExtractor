package model;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.jgrapht.EdgeFactory;
import org.jgrapht.Graph;
import org.jgrapht.UndirectedGraph;

public class UndirectGraph implements UndirectedGraph<Node, UndirectEdge>, Graph<Node, UndirectEdge> {

	private HashSet<Node> nodes;
	private HashSet<UndirectEdge> edges;
	private HashMap<String, Node> mapNameNode;
	
	public UndirectGraph() {
		this.nodes = new HashSet<Node>();
		this.edges = new HashSet<UndirectEdge>();
		this.mapNameNode = new HashMap<String, Node>();
	}
		
	public HashSet<Node> getNodes() {
		return nodes;
	}

	public void setNodes(HashSet<Node> nodes) {
		this.nodes = nodes;
	}

	public HashSet<UndirectEdge> getEdges() {
		return edges;
	}

	public void setEdges(HashSet<UndirectEdge> edges) {
		this.edges = edges;
	}
	
	public HashMap<String, Node> getMapNameNode() {
		return this.mapNameNode;
	}

	@Override
	public UndirectEdge addEdge(Node n1, Node n2) {
		UndirectEdge e = new UndirectEdge(n1, n2);
		this.edges.add(e);
		return e;
	}

	@Override
	public boolean addEdge(Node n1, Node n2, UndirectEdge e) {
		//
		return false;
	}

	@Override
	public boolean addVertex(Node arg0) {
		this.mapNameNode.put(arg0.getName(), arg0);
		return this.nodes.add(arg0);
	}

	@Override
	public boolean containsEdge(UndirectEdge e) {
		return this.edges.contains(e);
	}

	@Override
	public boolean containsEdge(Node n1, Node n2) {
		UndirectEdge u = new UndirectEdge(n1, n2);
		for(UndirectEdge urr : this.edges) {
			if (urr.equals(u))
				return true;
		}
		return false;
	}

	@Override
	public boolean containsVertex(Node nn) {
		for(Node n : this.nodes) {
			if (n.equals(nn))
				return true;
		}
		return false;
	}

	@Override
	public Set<UndirectEdge> edgeSet() {
		// TODO Auto-generated method stub
		return this.getEdges();
	}

	@Override
	public Set<UndirectEdge> edgesOf(Node n1) {
		Set<UndirectEdge> setEdges = new HashSet<UndirectEdge>();
		for(UndirectEdge e : this.edges) {
			if (e.getN1().equals(n1) || e.getN2().equals(n1))
				setEdges.add(e);
		}
		return setEdges;
	}

	@Override
	public Set<UndirectEdge> getAllEdges(Node n1, Node n2) {
		Set<UndirectEdge> setEdges = new HashSet<UndirectEdge>();
		for(UndirectEdge e : this.edges) {
			if ((e.getN1().equals(n1) && e.getN2().equals(n2)) ||
				(e.getN1().equals(n2) && e.getN2().equals(n1)))
				setEdges.add(e);
		}
		return setEdges;
	}

	@Override
	public UndirectEdge getEdge(Node n1, Node n2) {
		for(UndirectEdge e : this.edges) {
			if ((e.getN1().equals(n1) && e.getN2().equals(n2)) ||
				(e.getN1().equals(n2) && e.getN2().equals(n1)))
				return e;
		}
		return null;
	}

	@Override
	public EdgeFactory<Node, UndirectEdge> getEdgeFactory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node getEdgeSource(UndirectEdge n1) {
		return n1.getN1();
	}

	@Override
	public Node getEdgeTarget(UndirectEdge n1) {
		// TODO Auto-generated method stub
		return n1.getN2();
	}

	@Override
	public double getEdgeWeight(UndirectEdge arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean removeAllEdges(Collection<? extends UndirectEdge> arg0) {
		// TODO Auto-generated method stub
		return this.edges.removeAll(arg0);
	}

	@Override
	public Set<UndirectEdge> removeAllEdges(Node n1, Node n2) {
		HashSet<UndirectEdge> allEdge = new HashSet<UndirectEdge>();
		for(UndirectEdge e : this.edges) {
			if ((e.getN1().equals(n1) && e.getN2().equals(n2)) ||
				(e.getN1().equals(n2) && e.getN2().equals(n1))) {
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
	public boolean removeEdge(UndirectEdge arg0) {
		// TODO Auto-generated method stub
		return this.edges.remove(arg0);
	}

	@Override
	public UndirectEdge removeEdge(Node n1, Node n2) {
		// TODO Auto-generated method stub
		UndirectEdge ed = this.getEdge(n1, n2);
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
		return this.getNodes();
	}

	@Override
	public int degreeOf(Node n1) {
		return this.edgesOf(n1).size();
	}

	/*@Override
	public UndirectEdge addEdge(Node n1, Node n2) {
		//n1.addEdge(n2);
		UndirectEdge ed = new UndirectEdge(n1, n2);
		//n2.addEdge(n1);
		this.edges.add(ed);
		return ed;
	}
	
	
	public void addInderectEdge(Node n1, Node n2) {
		this.edges.add(new UndirectEdge(n1, n2));
	}
	
	@Override
	public boolean addEdge(Node n1, Node n2, UndirectEdge edge) {
		// TODO Auto-generated method stub
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
	
	@Override
	public boolean containsEdge(UndirectEdge arg0) {
		// TODO Auto-generated method stub
		return this.edges.contains(arg0);
	}

	@Override
	public boolean containsEdge(Node n1, Node n2) {
		// TODO Auto-geerated method stub
		for(UndirectEdge e : this.edges) {
			if ((e.getN1().equals(n1) && e.getN2().equals(n2)) ||
				 (e.getN1().equals(n2) && e.getN2().equals(n1)))
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
	public Set<UndirectEdge> edgeSet() {
		// TODO Auto-generated method stub
		return this.edges;
	}

	@Override
	public Set<UndirectEdge> edgesOf(Node n1) {
		// TODO Auto-generated method stub
		Set<UndirectEdge> edges = new HashSet<UndirectEdge>();
		for(UndirectEdge e : this.edges) {
			if (e.getN1().equals(n1) || e.getN2().equals(n1))
				edges.add(e);
		}
		return edges;
	}

	@Override
	public Set<UndirectEdge> getAllEdges(Node n1, Node n2) {
		HashSet<UndirectEdge> allEdge = new HashSet<UndirectEdge>();
		for(UndirectEdge e : this.edges) {
			if ((e.getN1().equals(n1) && e.getN1().equals(n2)) ||
				(e.getN2().equals(n2) && e.getN2().equals(n1)))
				allEdge.add(e);
		}
		return allEdge;
	}

	@Override
	public UndirectEdge getEdge(Node n1, Node n2) {
		// TODO Auto-generated method stub
		for(UndirectEdge e : this.edges) {
			if ((e.getN1().equals(n1) && e.getN2().equals(n2)) ||
				(e.getN1().equals(n2) && e.getN2().equals(n1)))
				return e;
		}
		return null;
	}

	@Override
	public EdgeFactory<Node, UndirectEdge> getEdgeFactory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node getEdgeSource(UndirectEdge arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node getEdgeTarget(UndirectEdge arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getEdgeWeight(UndirectEdge arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean removeAllEdges(Collection<? extends UndirectEdge> arg0) {
		// TODO Auto-generated method stub
		return this.edges.removeAll(arg0);
	}

	@Override
	public Set<UndirectEdge> removeAllEdges(Node n1, Node n2) {
		// TODO Auto-generated method stub
		HashSet<UndirectEdge> allEdge = new HashSet<UndirectEdge>();
		for(UndirectEdge e : this.edges) {
			if ((e.getN1().equals(n1) && e.getN2().equals(n2)) ||
				(e.getN1().equals(n2) && e.getN2().equals(n1))) {
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
	public boolean removeEdge(UndirectEdge arg0) {
		// TODO Auto-generated method stub
		return this.edges.remove(arg0);
	}

	@Override
	public UndirectEdge removeEdge(Node n1, Node n2) {
		// TODO Auto-generated method stub
		UndirectEdge ed = this.getEdge(n1, n2);
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
	}*/

}
