public class Edge{
	
	
	String ID;
	Node IntersectionA = null;
	Node IntersectionB = null;
	
	
	public Edge(String iD, Node intersectionA, Node intersectionB) {
		super();
		ID = iD;
		IntersectionA = intersectionA;
		IntersectionB = intersectionB;
	}
	
	public Edge() {
		
	}


	public String getID() {
		return ID;
	}


	public void setID(String iD) {
		ID = iD;
	}


	public Node getIntersectionA() {
		return IntersectionA;
	}


	public void setIntersectionA(Node intersectionA) {
		IntersectionA = intersectionA;
	}


	public Node getIntersectionB() {
		return IntersectionB;
	}


	public void setIntersectionB(Node intersectionB) {
		IntersectionB = intersectionB;
	}
	
	
	
	
	
	
}