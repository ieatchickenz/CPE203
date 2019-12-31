import java.lang.Math;

//THIS IS A TEST CHANGE

public class Point{
	// the x and y coordiantes
	private double x;
	private double y;

//------------------------------------------------------------------------------

   //One Constructor

	public Point(double x, double y){
	   this.x = x;
	   this.y = y;
	   }

//-----------------------------------------------------------------------------

   //Methods

	//Returns x
	public double getX() {return x;}
	
	//Returns y
	public double getY() {return y;}
	
	//Returns the y-coordinate of this point
	public double getRadius(){
	   double y_sqr = y * y;
	   double x_sqr = x * x;
	   double sum = x_sqr + y_sqr;
	   double dis = Math.sqrt(sum);
	   return dis;
	   }

	//Returns the angle (in radians) from the x-axis in the in range of -pi to pi
        public double getAngle(){
	   double num = y/x;
	   double ans = Math.atan(num);
	   return ans;
	   }

	//Returns a newly created Point representing a 90-degree (counterclockwise) rotation of this point about the origin.
	public Point rotate90(){
	   double new_y = y * -1;
	   double new_x = x * -1;
	   Point rot_point = new Point(new_x, new_y);
	   return rot_point;
	   }

}
