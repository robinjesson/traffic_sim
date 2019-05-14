package graph;
import java.util.HashMap;

public class Dijkstra {
	private HashMap<Vertex,Integer> d=new HashMap<>();
	
	public static void shortestPath(Vertex start, Vertex end, Graph g) {
		
	}
	
	public void initialization(Graph g, Vertex start) {
		for(Vertex v:g.getVertices()) {
			d.put(v,10000000);
		}
		
	}
}
