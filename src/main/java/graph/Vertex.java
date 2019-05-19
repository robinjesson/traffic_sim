package graph;

public class Vertex {
	private double x;
	private double y;
	
	public Vertex(double x,double y) {
		this.x=x;
		this.y=y;
	}
	
	public double getX() {
		return this.x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return this.y;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean res=false;
		if(obj==this) {
			res=true;
		}
		else {
			if(obj instanceof Vertex) {
				Vertex n=(Vertex)obj;
				res=this.x==n.x && this.y==n.y;
			}
		}
		return res;
	}

	@Override
	public String toString() {
		return "(" + x + "," + y + ")";
	}
	
	
	
}
