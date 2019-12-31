import java.util.ArrayList;
import java.util.List;

public class Bigger{
   private static double large;
   private static double largest; 

   public static double whichIsBigger(Circle circle, Rectangle rectangle, Polygon polygon){
	double a = circle.perimeter();
	double b = rectangle.perimeter();
	double c = polygon.perimeter();

	if(a > b){
	   large = a;
	   }
	else{
	   large = b; 
	   }

	if(large > c){
	   largest = large;
	   }
	else{
	   largest = c;
	   }
	return largest;
	}
}
