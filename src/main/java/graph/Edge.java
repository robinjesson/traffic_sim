package graph;

public class Edge {
	private Vertex node1;
	private Vertex node2;
	private double weight;
	
	public Edge(Vertex node1, Vertex node2,double weight) {
		this.node1 = node1;
		this.node2 = node2;
		this.weight=weight;
	}

	public Vertex getNode1() {
		return node1;
	}

	public void setNode1(Vertex node1) {
		this.node1 = node1;
	}

	public Vertex getNode2() {
		return node2;
	}

	public void setNode2(Vertex node2) {
		this.node2 = node2;
	}
	
	@Override
	public boolean equals(Object o) {
		boolean res=false;
		if(this==o) {
			res=true;
		}
		else {
			if(o instanceof Edge) {
				Edge e=(Edge) o;
				res=(this.node1.equals(e.node1) && this.node2.equals(e.node2)) || (this.node1.equals(e.node2) && this.node2.equals(e.node1));
			}
		}
		return res;
	}

	@Override
	public String toString() {
		return "{"+node1 + ","+ node2 + ":" +weight +"}";
	}
	
	public double getWeight() {
		return this.weight;
	}
	
	
	
}
