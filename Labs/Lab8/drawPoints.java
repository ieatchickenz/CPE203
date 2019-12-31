import processing.core.*;

import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class drawPoints extends PApplet {

	public void settings() {
    size(500, 500);
	}
  
	public void setup() {
    	background(180);
    	noLoop();
  	}

  	public static void readInPoints(List<Point> points){
		try{
			Scanner sc = new Scanner(new File("positions.txt"));
			while (sc.hasNextLine()){
				String out = sc.nextLine();
				String[] p = out.split(", ");
				points.add(new Point(Double.parseDouble(p[0]),
									 Double.parseDouble(p[1]),
								     Integer.parseInt(p[2])));
				sc.nextLine();
			}
		}
		catch (Exception e){
			System.out.println("Could not open input file.");
		}

	}

	public static void writePoints(List<Point> points){
		try{
			PrintStream ps = new PrintStream("drawMe.txt");
			for (Point p : points){
				ps.print(p.getX());
				ps.print(", ");
				ps.print(p.getY());
				ps.print(", ");
				ps.println(p.getZ());
			}
		}
		catch (Exception e){
			System.out.println("Can't open this output file");
		}
	}

  	public void draw() {

   	double x, y;
   	List<Point> drawPoint = new ArrayList<>();
   	readInPoints(drawPoint);
   	List<Point> modPoints = drawPoint.stream().filter(a -> a.getZ() <= 2).map(p -> p.scalePoint()).map(p -> p.translate()).collect(Collectors.toList());
   	writePoints(modPoints);
   
   	String[] lines = loadStrings("drawMe.txt");
   	println("there are " + lines.length);
  		for (int i=0; i < lines.length; i++){
      	if (lines[i].length() > 0 ) {
        		String[] words= lines[i].split(",");
        		x = Double.parseDouble(words[0]);
        		y = Double.parseDouble(words[1]);
        		println("xy: " + x + " " + y);
        		ellipse((int)x, (int)y, 1, 1);
      	}
  		}
  	}

  	public static void main(String args[]) {
      PApplet.main("drawPoints");
   }
}
