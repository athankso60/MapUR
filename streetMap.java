import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class StreetMap extends JComponent {
	public static void main(String[] args) {
		File f = new File(args[0]);
		Graph g = new Graph();
		g.buildGraph(f);
		
		boolean ShouldShow = false;
		boolean ShouldFind = false;
		int startindex = 0;
		
		for(int i = 1; i< args.length-1; i++) {
			if(args[i] == "-- show") {
				ShouldShow = true;
			}else if(args[i] == "-- directions") {
				ShouldFind = true;
				startindex = i+1;
			}
		}
		
		if(ShouldFind) {
			Node start = new Node();
			Node end = new Node();
			for(Node n: g.nodes) {
				if(n.ID.equals(args[startindex])) {
					start = n;
				}
				if(n.ID.equals(args[startindex +1])) {
					end = n;
				}
			}
			try {
				double distance  = 0;
				ArrayList<Graph.Edge> path = g.PathFinder(start, end);
				
				for(Graph.Edge e: path) {
					System.out.println(e.id);
					distance = distance + e.weight;
				}
				System.out.println(distance + "m"+"  traveled");
			}catch(NullPointerException n) {
				System.out.println("didn't input the start ID and end ID correctly");
			}
		}
		
		if(ShouldShow) {
			JFrame frame = new JFrame();
			Graphics G = frame.getGraphics();
			frame.add(g);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(1700,1700);
			frame.setVisible(true);
			g.paintComponent(G);
		}
		
	}
}
	

