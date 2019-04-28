/** Edge class for Adjacency List graph representation 
 * 
 * [Dr._Clifford_A._Shaffer]_Data_Structures_and_Algo
 * 
 * */
class Edge {
	private int[] vertices;
	private int vert, wt;
	public Edge(int v, int w) // Constructor
	{ vert = v; wt = w; }
	public Edge(int[] v, int w) {
		vertices = v; wt = w;
	}
	
	public int vertex() { return vert; }
	
	public int weight() { return wt; }
}