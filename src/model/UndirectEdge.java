package model;

public class UndirectEdge {
	private Node n1;
	private Node n2;

	public UndirectEdge(Node from, Node to) {
		this.n1 = from;
		this.n2 = to;
	}

	public Node getN1() {
		return n1;
	}

	public void setN1(Node from) {
		this.n1 = from;
	}

	public Node getN2() {
		return n2;
	}



	public void setN2(Node to) {
		this.n2 = to;
	}



	@Override
	public boolean equals(Object obj) {
		UndirectEdge e = (UndirectEdge)obj;
		return e.getN1().equals(n1) && e.getN2().equals(n2);
	}
}
