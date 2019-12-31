import java.util.*;
import java.lang.Math;

public class Util{
   //Methods
   public static double perimeter(Circle circle){
	double radius = circle.getRadius();
	double perim = (2*radius) * Math.PI;
	return perim;
	} 

   public static double perimeter(Rectangle rectangle){
	double x1 = rectangle.getTopLeft().getX();
	double y1 = rectangle.getTopLeft().getY();
	
	double x2 = rectangle.getBottomRight().getX();
	double y2 = rectangle.getBottomRight().getY();

	double length = Math.abs(x2 - x1);
	double width = Math.abs(y1 - y2);

	double perim = 2 * (length + width);
	return perim;
	}
   
   public static double perimeter(Polygon polygon){
	List<Point> points = polygon.getPoints();
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
