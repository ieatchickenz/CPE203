import java.lang.Math;

public class Circle{
   
   private double radius;
   private Point center;

//Constructor-----------------
   public Circle(){}

   public Circle(Point center, double radius){
	this.center = center;
	this.radius = radius;
	}

//Methods------------------
    public double getRadius(){
	return this.radius;
	}

    public Point getCenter(){
	return this.center;
	}
    
    public double perimeter(){
	double perim = (2* this.radius) * Math.PI;
	return perim;
	}
}
