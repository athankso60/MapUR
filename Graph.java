 // A Java program for Bellman-Ford's single source shortest path 
// algorithm. 
import java.util.*;

import javax.swing.JComponent;
import javax.swing.JFrame;

import java.lang.*;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Line2D;

import javax.swing.SwingUtilities;
import java.io.*; 

// A class to represent a connected, directed and weighted graph 
class Graph extends JComponent
{
	int height = getHeight();
	int width = getWidth();
	ArrayList<Edge> pathfound = new ArrayList<>();
	
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
    PriorityQueue<Node> nodesrelaxed= new PriorityQueue<>();
    
    public ArrayList<Edge> PathFinder(Node start, Node end){
    	ArrayList<Edge> path = new ArrayList<Edge>();
    	
    	this.dijkstra(start);
    	
    	path = this.finderhelper(path, end);
    	
    	pathfound = path;
    	return path;
    }
    
    public ArrayList<Edge> finderhelper(ArrayList<Edge> path, Node n){
    	//end condition
    	if(n.parent.ID == n.ID) {
    		return path;
    	}
    	
    	//recursive steps
    	for(Edge e: n.edges) {
    		if(n.parent.ID.equals(e.dest.ID)) {
    			path.add(e);
    			return this.finderhelper(path, e.dest);
    		}
    	}
    	return null;
    }
	
	//Implementation of Dijsktra algorithm
	public void dijkstra(Node src){
		this.initialize(src);
		
		this.relax(src);
	}
	
	
	 
	 public void relax(Node n) {
		//recursive steps
		double startweight = n.weight;
		//PriorityQueue<Node> visited = new PriorityQueue<>();
		for(Edge e: n.edges) {
			//n.edges.get(i).dest
			double length = e.weight;
			double desweight = e.dest.weight;

			if((startweight+length)<desweight) {
				e.dest.weight = startweight+length;
				e.dest.parent = n;
				nodesrelaxed.add(e.dest);
			}
			
			//System.out.println(e.dest.ID+"         Visited");//
		}
		
		//end condition: no more stuff in the Priority Queque
		try {
		Node node = nodesrelaxed.remove();
		//System.out.println(node);//
		relax(node);
		}catch(RuntimeException r) {
			//System.out.println(r);
		}
	 }
	 
	 public void initialize(Node src) {
		 
		 for(Node n: this.nodes) {
			 n.weight = Double.MAX_VALUE;
			 //this.nodes_pq.add(n);
		 }
		 src.weight = 0;
		 //this.nodes_pq.add(src);
		 src.parent = src;
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
					 
					if(ar[0].equals("i")) {
						Node Intersection = new Node(ar[1],Double.parseDouble(ar[2]),Double.parseDouble(ar[3]));
						
						i++;
						
						//Adding
						nodes.add(Intersection);
						//nodes_pq.add(Intersection);
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
						//adding the road back
						Edge roadback = new Edge(road.id);
						roadback.src = road.dest;
						roadback.dest = road.src;
						road.dest.edges.add(roadback);
					}
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
	
// 	public void drawMap(Graph graph, Graphics g) {
// 		ArrayList<Graph.Edge> edgeList = new ArrayList<Graph.Edge>();//adjacency list of current node
// 		Node currNode;//current node
		
// 		for (int i = 0; i < graph.nodes.size(); i++ ) {
// 			edgeList = graph.nodes.get(i).edges;
// 			currNode = graph.nodes.get(i);
			
// 			for(int j=0; j<edgeList.size(); j++) {
// 				g.drawLine((int)edgeList.get(i).src.longitude,(int) edgeList.get(i).src.lattitude,(int) edgeList.get(i).dest.longitude,(int)edgeList.get(i).dest.lattitude);//MIke will do the calculations to draw correctly
// 			}
// 		}
// 	}
	
	//This method does the drawing of the map
	public void paintComponent(Graphics g) {
		
		g.setColor(Color.RED);
		
		ArrayList<Graph.Edge> edgeList = new ArrayList<Graph.Edge>();//adjacency list of current node
		Node currNode;//current node
		
		int index = this.nodes.size()/2;
		double centerlat = this.nodes.get(index).lattitude;
		double centerlong = this.nodes.get(index).longitude;
		
		index = this.nodes.size()-1;
		double latdif = (centerlat - this.nodes.get(index).lattitude);
		double longdif = centerlong - this.nodes.get(index).longitude;
		
		int x=10;
		int y = (int)(1700/(10*longdif));
		x = (int) (1700/(10*latdif));
		
		for (int i = 0; i <this.nodes.size(); i++ ) {
			edgeList = this.nodes.get(i).edges;
			currNode = this.nodes.get(i);
			
			
			
			for(int j=0; j<edgeList.size(); j++) {
//				double startX = edgeList.get(i).src.longitude;
//				double startY = edgeList.get(i).src.lattitude;
//				double endX = edgeList.get(i).dest.longitude;
//				double endY = edgeList.get(i).dest.lattitude;
//				System.out.println((int)(Math.abs(startX - 43.121)*1000000)+" "+(int)(Math.abs(startY + 77.630)*1000000)+" "+(int)(Math.abs(endX - 43.121)*1000000)+" "+(int) (Math.abs(endY+77.630)*1000000));
//				//g.drawLine((int)edgeList.get(i).src.longitude,(int) edgeList.get(i).src.lattitude,(int) edgeList.get(i).dest.longitude,(int)edgeList.get(i).dest.lattitude);//Mike will do the calculations to draw correctly
//				g.drawLine((int)(Math.abs(startX - 43.121)*100000), (int)(Math.abs(startY + 77.630)*100000), (int)(Math.abs(endX - 43.121)*100000),(int) (Math.abs(endY+77.630)*100000));
				
				double startX = (edgeList.get(j).src.longitude - centerlong)*y+500;
				double startY = (edgeList.get(j).src.lattitude - centerlat)*x+500;
				double endX = (edgeList.get(j).dest.longitude - centerlong)*y+500;
				double endY = (edgeList.get(j).dest.lattitude - centerlat)*x+500;
				
				System.out.println(startX+" " +startY+" " +endX+" " +endY);
				g.drawLine((int)startX, (int)startY, (int)endX, (int)endY);
				//g.drawLine(1000, 1000, 1044, 993);
				}
		}
	}
	
	//This method is supposed to show the map when called
		public void showPath(Graphics g) {
		ArrayList<Graph.Edge>edges = pathfound;
//		Graphics2D g2d = (Graphics2D) g;
//		g2d.setColor(Color.RED);
//		Stroke stroke = new BasicStroke(2f);
//		g2d.setStroke(stroke);
		
//		g.setColor(Color.red);
		
		double startX;
		double startY;
		double endX;
		double endY;
		for(int i =0; i < edges.size(); i++) {
			startX = edges.get(i).src.longitude;
			startY = edges.get(i).src.lattitude;
			endX = edges.get(i).dest.longitude;
			endY = edges.get(i).dest.lattitude;
			//g.setColor(Color.red);
			g.drawLine((int)(startX),(int)(startY),(int)(endX),(int)(endY));
		}
	}
	
	
	
	
	public static void main(String[] args) {
		File f = new File("ur.txt");
		Graph g = new Graph();
		g.buildGraph(f);
		
		ArrayList<Edge>path = g.PathFinder(g.nodes.get(0), g.nodes.get(25));
		
		JFrame frame = new JFrame();
		Graphics G = frame.getGraphics();
		g.showPath(G); // uncomment this to show path. Might be problematic
		frame.add(g);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1700,1700);
		frame.setVisible(true);
	}
} 
