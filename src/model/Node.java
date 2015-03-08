package model;

import java.util.HashSet;


public class Node  {
	private String name;
    /*private HashSet<Edge> inEdges;
    private HashSet<Edge> outEdges;*/
    
    public Node(String name) {
      this.name = name;
      /*inEdges = new HashSet<Edge>();
      outEdges = new HashSet<Edge>();*/
    }
    
    
    
    public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}



	/*public HashSet<Edge> getInEdges() {
		return inEdges;
	}

	public void setInEdges(HashSet<Edge> inEdges) {
		this.inEdges = inEdges;
	}

	public HashSet<Edge> getOutEdges() {
		return outEdges;
	}


	public void setOutEdges(HashSet<Edge> outEdges) {
		this.outEdges = outEdges;
	}

	public Node addEdge(Node node){
      Edge e = new Edge(this, node);
      outEdges.add(e);
      node.inEdges.add(e);
      return this;
    }*/
	
	@Override
	public boolean equals(Object obj) {
		Node e = (Node)obj;
		return e.getName().equals(this.name);
	}
	
    @Override
    public String toString() {
      return name;
    }
}
