import java.util.LinkedList;

//This class creates nodes of graphs
public class Node {
	
	String ID;
	double longitude;
	double lattitude;
	int weight = 0;
	private LinkedList<Edge>edges = new LinkedList<Edge>();
	
	
	
	public Node(String iD, double longitude, double lattitude) {
		super();
		ID = iD;
		this.longitude = longitude;
		this.lattitude = lattitude;
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
		return weight;
	}
	
	public void setWeight(int n) {
		this.weight = n;
	}
	
	public void addEdge(Edge e) {
        edges.add(e);
    }
	
	public LinkedList getEdges() {
        return edges;
    }
	
	//get an edge
	 public Edge getEdge(Node v) {
         for (Edge e : edges) {
             if (e.IntersectionB.equals(v))
                 return e;
         }
         return null;
     }
	 
	 //check if there is a path to
	 public boolean pathTo(Node v) {
         for (Edge e : edges) {
             if (e.IntersectionB.equals(v))
                 return true;
         }
         return false;
     }
	
	
	
	
}
