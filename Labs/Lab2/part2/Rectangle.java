public class Rectangle{
    
    private Point topLeft;
    private Point bottomRight;

//Constructor----------------------
    public Rectangle(){}

    public Rectangle(Point topLeft, Point bottomRight){
	this.topLeft = topLeft;
	this.bottomRight = bottomRight;
	}

//Methods--------------------------
    public Point getTopLeft(){
	return this.topLeft;
	}

    public Point getBottomRight(){
	return this.bottomRight;
	}
    
    public double perimeter(){
	double length = Math.abs(this.bottomRight.getX() - this.topLeft.getX());
	double width = Math.abs(this.bottomRight.getY() - this.topLeft.getY());
	
	double perim = 2 * (length + width);
	return perim;
	}
}
