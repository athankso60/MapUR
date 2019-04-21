import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JComponent;

public class streetMap extends JComponent {
	
	int width = getWidth();
	int height = getHeight();
	
	HaversineAlgorithm harvesine = new HaversineAlgorithm();

	public Graph buildGraph(String x){
		try {
			BufferedReader in = new BufferedReader(new FileReader("input"));
			//String x;
			try {
				x = in.readLine();
				String[] ar = x.split("	");
				
				int i = 0;
				while(x!=null) {
					
				}
				
				
				
			} catch (IOException e) {
				System.out.println("file is empty");
			}
		} catch (FileNotFoundException e) {
			System.out.println("can't find file");
		}
		
		
		
		
		
		
		
		return null;
	}
}
	

