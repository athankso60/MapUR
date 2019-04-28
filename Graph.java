// A Java program for Bellman-Ford's single source shortest path 
// algorithm. 
import java.util.*; 
import java.lang.*; 
import java.io.*; 

// A class to represent a connected, directed and weighted graph 
class Graph 
{ 
	
	
    private int V; // Number of vertices
	// A class to represent a weighted edge in graph 
	class Edge { 
		Node src, dest;
		double weight; 
		String id;
		
		public Edge() { 
			src = dest = null;
			id = null;
			weight = 0; 
		}
		
		public Edge(String name) {
			src = dest = null;
			id = name;
			weight = 0; 
		}
		
		public double getWeight() {
			return this.weight;
		}
		
		public void setWeight(double n) {
			this.weight = n;
		}
		
		
	} 

	//int V, E; 
	//ArrayList <Edge> edge = new ArrayList<Edge>(); 
	ArrayList<Node> nodes = new ArrayList<Node>();
//	private int dist[]; 
//    private Set<Integer> settled; 
//    private PriorityQueue<Node> pq; 

	// Creates a graph with V vertices and E edges 
	Graph() 
	{ 
		
	}
	
	
	//Implementation of Dijsktra algorithm using priority Queue
	class DPQ { 
	    private int dist[]; 
	    private Set<Node> settled; 
	    private PriorityQueue<Node> pq; 
	    private int V; // Number of vertices 
	    List<List<Node>> adj; 
	  
	    public DPQ(int V) 
	    { 
	    	//V would be the size of the nodes array
	        this.V = V; 
	        dist = new int[V]; 
	        settled = new HashSet<Node>(); 
	        pq = new PriorityQueue<Node>(V); 
	    } 
	  
	    // Function for Dijkstra's Algorithm 
	    public void dijkstra(List<List<Node> > adj, Node src) 
	    { 
	        this.adj = adj; 
	  
	        for (int i = 0; i < V; i++) 
	            dist[i] = Integer.MAX_VALUE; 
	  
	        // Add source node to the priority queue 
	        
	        pq.add(new Node(src.ID,src.longitude,src.lattitude)); 
	  
	        // Distance to the source is 0 
	        dist[src.nodeNumber] = 0; 
	        src.weight = 0;
	        while (settled.size() != V) { 
	  
	            // remove the minimum distance node  
	            // from the priority queue  
	        	Node u = pq.remove(); 
	  
	            // adding the node whose distance is 
	            // finalized 
	            settled.add(u); 
	  
	            e_Neighbours(u.nodeNumber); 
	        } 
	    } 
	  
	    // Function to process all the neighbours  
	    // of the passed node 
	    private void e_Neighbours(int u) 
	    //u here is the node number of the current node
	    { 
	        int edgeDistance = -1; 
	        int newDistance = -1; 
	  
	        // All the neighbors of v 
	        for (int i = 0; i < adj.get(u).size(); i++) { 
	            Node v = adj.get(u).get(i); 
	  
	            // If current node hasn't already been processed 
	            if (!settled.contains(v)) { 
	                edgeDistance = v.weight; 
	                newDistance = dist[u] + edgeDistance; 
	  
	                // If new distance is cheaper in cost 
	                if (newDistance < dist[v.nodeNumber]) 
	                    dist[v.nodeNumber] = newDistance; 
	  
	                // Add the current node to the queue 
	              //String iD, double longitude, double lattitude
	                pq.add(new Node(v.ID, v.longitude,v.lattitude)); 
	            } 
	        } 
	    } 
	}
	
	
	
	
	public void buildGraph(File f) {
		
		//int V =10, E = 10;
		
		
		try {
			BufferedReader in = new BufferedReader(new FileReader(f));
			String line;
			try {
				
				//in.readLine();
				int i = 0;
				while((line=in.readLine())!=null) {
					
					String[] ar = line.split(" ");
					 
					if(ar[0].equals("i")) {
						Node Intersection = new Node(ar[1],Double.parseDouble(ar[2]),Double.parseDouble(ar[3]));
						Intersection.nodeNumber = i;
						i++;
						nodes.add(Intersection);
					}
					else if(ar[0].equals("r")) {
						Edge road = new Edge(ar[1]);
						boolean srcFound = false, desFound = false;
						for(int j = 0; j< nodes.size(); j++) {
							
							if(nodes.get(j).ID.equals(ar[2])) {
								
								nodes.get(j).edges.add(road);
								road.src = nodes.get(j);
								srcFound = true;
								
							}
							
							else if(nodes.get(j).ID.equals(ar[3])) {
								road.dest = nodes.get(j);
								desFound = true;
							}
							
							if(srcFound == true && desFound == true) {
								break;
							}
						}
						
						double weight = HaversineAlgorithm.distanceInKm(road.src.getLattitude(), road.src.getLongitude(), road.dest.getLattitude(), road.dest.getLongitude());
						road.setWeight(weight);			
					}
					
					
					
					
					
					//System.out.println(i + " weight:"+ Integer.parseInt(ar[2]));
					i++;
				
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println(e);
				e.printStackTrace();
			}
		}catch(FileNotFoundException e) {
			System.out.println(e);
		}
		
	}
	
//	public static void main(String[] args) {
//		File f = new File(".txt");
//		Graph g = new Graph();
//		g.buildGraph(f);
//		//use g afterwards as the graph obj
//		
//		
//	}

	
} 
