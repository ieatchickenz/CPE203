import java.util.*;

public class Polygon{

    private List<Point> points;

//Constructors----------------------

    public Polygon(){}

    public Polygon(List<Point> points){
	this.points = points;
	}	
//Method---------------------
    public List<Point> getPoints(){
	return this.points;
	}

    public double perimeter(){
	double tot_dist = 0.0;
	for(int index = 0; index < points.size() - 1; index++){
	   Point point1 = points.get(index);
	   Point point2 = points.get(index + 1);
	   double dist = getDistance(point1, point2);
	   tot_dist += dist;
	   }
	double fin = getDistance(points.get(0), points.get(points.size()-1));
	tot_dist += fin;
	return tot_dist;
	}

   private static double getDistance(Point p1, Point p2){
	double x1 = p1.getX();
	double y1 = p1.getY();

	double x2 = p2.getX();
	double y2 = p2.getY();

	double x_val = Math.pow(x1 - x2, 2.0);
	double y_val = Math.pow(y1 - y2, 2.0);

	double distance = Math.sqrt(x_val + y_val);
	return distance;
	}
    
}
