import java.util.ArrayList;
import java.util.LinkedList;

//This class creates nodes of graphs
public class Node implements Comparable<Node>{
	
	String ID;
	double longitude;
	double lattitude;
	double weight;
	public Node parent;
	
	public ArrayList<Graph.Edge>edges = new ArrayList<Graph.Edge>();
	
	
	
	@Override
	public String toString() {
		return "Node [ID=" + ID + ", longitude=" + longitude + ", lattitude=" + lattitude + ", weight=" + weight
				+ ", edges=" + edges + "]";
	}




	public Node(String iD, double longitude, double lattitude) {
		super();
		ID = iD;
		this.longitude = longitude;
		this.lattitude = lattitude;
		this.weight = 0;
		this.parent = null;
	}
	
	public Node getParent() {
		return this.parent;
	}
	
	public void setParent(Node n) {
		this.parent = n;
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
	
	public double getWeight() {
		return this.weight;
	}
	
	public void setWeight(double n) {
		this.weight = n ;
	}
	
	public int compareTo(Node o) {
		if(this.weight < o.weight) {
			return -1;
		}else if(this.weight == o.weight) {
			return 0;
		}else {
			return 1;
		}
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
