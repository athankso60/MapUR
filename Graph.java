// A Java program for Bellman-Ford's single source shortest path 
// algorithm. 
import java.util.*;

import javax.swing.JComponent;

import java.lang.*;
import java.awt.Graphics;
import java.io.*; 

// A class to represent a connected, directed and weighted graph 
class Graph 
{ 
//	int height = getHeight();
//	int width = getWidth();
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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


	ArrayList<Node> nodes = new ArrayList<Node>();

	// Creates a graph with V vertices and E edges 
	Graph() 
	{ 
		for(int i = 0; i < nodes.size();i++) {
			nodes.get(i).nodeNumber = i;
		}
	}
	
	
	//Implementation of Dijsktra algorithm using priority Queue
	public class DPQ { 
	    private int dist[]; 
	    private Set<Node> settled; 
	    private PriorityQueue<Node> pq; 
	    private int V; // Number of vertices 
	    ArrayList<ArrayList<Edge>> adj; 
	    public Graph graph;
	  
	    public DPQ(Graph g) 
	    { 
	    	//V would be the size of the nodes array
	        this.V = g.nodes.size(); 
	        dist = new int[V]; 
	        settled = new HashSet<Node>(); 
	        pq = new PriorityQueue<Node>(); 
	        ArrayList<ArrayList<Edge>> a = new ArrayList<ArrayList<Edge>>();
	        for(Node E:g.nodes) {
	        	a.add(E.edges);
	        	for(Edge e: E.edges) {
	        		//System.out.println("edge id: " +e.id + "weight" + e.weight );
	        	}
	        }
	        this.adj = a;
	        this.graph = g;
	    } 
	  
	    // Function for Dijkstra's Algorithm 
	    public void dijkstra(Node src) 
	    { 
	  
	        for (int i = 0; i < V; i++) { 
	            dist[i] = Integer.MAX_VALUE; 
	            graph.nodes.get(i).weight = Integer.MAX_VALUE;
	        }
	        // Add source node to the priority queue 
	        
	        pq.add(new Node(src.ID,src.longitude,src.lattitude)); 
	        
	        // Distance to the source is 0 
	        dist[src.nodeNumber] = 0; 
	        //src.weight = 0;
	        System.out.println("src Node Number = "+ src.nodeNumber);
	        while (settled.size() != V && pq!=null) { 
	  
	            // remove the minimum distance node  
	            // from the priority queue  
	        	Node u = pq.remove(); 
	        	System.out.println("u's id: " +u.ID);
	  
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
	            Node v = adj.get(u).get(i).src; 
	  
	            // If current node hasn't already been processed 
	            if (!settled.contains(v)) { 
	                edgeDistance = v.weight; 
	                newDistance = dist[u] + edgeDistance; 
	                
	                System.out.println("e dis: "+edgeDistance);
	                System.out.println("n dis: "+newDistance);
	                // If new distance is cheaper in cost 
	                if (newDistance < dist[v.nodeNumber]) 
	                    dist[v.nodeNumber] = newDistance;
	                	v.weight = newDistance;
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
					
					String[] ar = line.split("	");
					
					System.out.println(ar[0]);
					 
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
	
	public void drawMap(Graph graph, Graphics g) {
		ArrayList<Graph.Edge> edgeList = new ArrayList<Graph.Edge>();//adjacency list of current node
		Node currNode;//current node
		
		for (int i = 0; i < graph.nodes.size(); i++ ) {
			edgeList = graph.nodes.get(i).edges;
			currNode = graph.nodes.get(i);
			
			for(int j=0; j<edgeList.size(); j++) {
				g.drawLine((int)edgeList.get(i).src.longitude,(int) edgeList.get(i).src.lattitude,(int) edgeList.get(i).dest.longitude,(int)edgeList.get(i).dest.lattitude);//MIke will do the calculations to draw correctly
			}
		}
		
	}
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
		File f = new File("ur.txt");
		Graph g = new Graph();
		g.buildGraph(f);
		//System.out.println(g.nodes.size());
		
		DPQ d = g.new DPQ(g);
		d.dijkstra(g.nodes.get(1));
		for(int i=0; i<d.dist.length; i++) {
			System.out.print("d:  ");
			System.out.println(d.dist[i]);
			System.out.print("g:  ");
			System.out.println(g.nodes.get(i).weight);

		}

			//System.out.println(g.nodes.get(1).nodeNumber);
		
		
	
	}

	
} 
