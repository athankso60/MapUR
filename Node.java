import java.util.ArrayList;
import java.util.LinkedList;

//This class creates nodes of graphs
public class Node {
	
	String ID;
	double longitude;
	double lattitude;
	int weight;
	public int nodeNumber;

	public ArrayList<Graph.Edge>edges = new ArrayList<Graph.Edge>();
	
	
	
	public Node(String iD, double longitude, double lattitude) {
		super();
		ID = iD;
		this.longitude = longitude;
		this.lattitude = lattitude;
		this.weight = 0;
		this.nodeNumber = 0;
	}
	
	
	
	
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLattitude() {
		return lattitude;
	}
	public void setLattitude(double lattitude) {
		this.lattitude = lattitude;
	}
	
	public int getWeight() {
		return this.weight;
	}
	
	public void setWeight(int n) {
		this.weight = n ;
	}
	
	public int getNodeNumber() {
		return this.nodeNumber;
	}
	
	public void setNodeNumber(int n) {
		this.nodeNumber = n ;
	}
	

	
//	public void addEdge(Graph.Edge e) {
//        edges.add(e);
//    }
//	
//	public LinkedList getEdges() {
//        return edges;
//    }
	
	//get an edge
//	 public Graph.Edge getEdge(String v) {
//         for (Graph.Edge e : edges) {
//             if (e.dest.ID.equals(v))
//                 return e;
//         }
//         return null;
//     }
//	 
//	 //check if there is a path to
//	 public boolean havePathTo(String v) {
//         for (Graph.Edge e : edges) {
//             if (e.dest.ID.equals(v))
//                 return true;
//         }
//         return false;
//     }
	
	
	
	
}
