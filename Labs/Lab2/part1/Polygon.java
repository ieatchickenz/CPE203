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
}
