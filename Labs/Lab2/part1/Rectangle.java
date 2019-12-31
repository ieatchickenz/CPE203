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
}
