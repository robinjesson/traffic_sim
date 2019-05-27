package graph;

import java.util.ArrayList;

import road_elements.Road;



public class Graph {
	private ArrayList<Edge> edges;
	private ArrayList<Vertex> vertices;
	
	public Graph() {
		this.edges=new ArrayList<>();
		this.vertices=new ArrayList<>();
	}

	public Graph(ArrayList<Edge> edges, ArrayList<Vertex> Vertexs) {
		super();
		this.edges = edges;
		this.vertices = Vertexs;
	}

	public static Graph createFromRoads(ArrayList<Road> roads) {
		Graph g=new Graph();
		for(Road road:roads) {
			Vertex vertex1=new Vertex(road.getBeginX(), road.getBeginY());
			Vertex vertex2=new Vertex(road.getEndX(), road.getEndY());
			
			if(!g.vertices.contains(vertex1))
				g.vertices.add(vertex1);
			if(!g.vertices.contains(vertex2))
				g.vertices.add(vertex2);
			
			Edge edge=new Edge(vertex1,vertex2,road.getDistanceKilometers());
			
			if(!g.edges.contains(edge))
				g.edges.add(edge);
				
		}
		return g;
	}
	
	public ArrayList<Vertex> getVertices(){
		return this.vertices;
	}
	
	@Override
	public String toString() {
		return this.vertices.toString() +'\n'+this.edges.toString();
	}
	
	public Edge getEdge(Vertex v1,Vertex v2) {
		Edge res=null;
		for(Edge e:this.edges) {
			if(e.equals(new Edge(v1, v2, 0))) {
				return e;
			}
		}
		return res;
	}
	
	public ArrayList<Vertex> neighbours(Vertex v){
		ArrayList<Vertex> vertices=new ArrayList<Vertex>();
		for(Edge e:this.edges) {
			if(e.getNode1().equals(v)) 
				vertices.add(e.getNode2());
			if(e.getNode2().equals(v))
				vertices.add(e.getNode1());
		}
		return vertices;
	}
	
	public Vertex closestVertex(Vertex v) {
		Vertex res=this.neighbours(v).get(0);
		for(Vertex vertex:this.neighbours(v)) {
			Edge eFound=this.getEdge(v, vertex);
			Edge eRes=this.getEdge(v, res);
			System.out.println(eFound);
			System.out.println(eRes);
			if(eFound.getWeight()<=eRes.getWeight()) {
				res=vertex;
			}
		}
		return res;
		
	}
	

	
	public static void main(String[]args) {
		ArrayList<Road> roadList = new ArrayList<Road>();
		roadList.add(new Road(0, 0, 0, 150, 50));
		roadList.add(new Road(0,100,100,100,50));
		roadList.add(new Road(100,100,100,-50,50));
		roadList.add(new Road(100, 0, 0, 0, 50));


		roadList.add(new Road(-50, -50, -50, 150, 130));
		roadList.add(new Road(-50, 150, 150, 150, 130));
		roadList.add(new Road(150, 150, 150, -50, 130));
		roadList.add(new Road(150, -50, -50, -50, 130));
		
		Vertex v1=new Vertex(0,0);
		Vertex v2=new Vertex(0,150);
		
		Graph g=Graph.createFromRoads(roadList);
		System.out.println(g);
		System.out.println();
		System.out.println(g.closestVertex(v1));
		
		
	}
}
