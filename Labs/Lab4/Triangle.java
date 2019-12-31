import java.awt.*;

public class Triangle implements Shape {
    private Point vertexA;
    private Point vertexB;
    private Point vertexC;
    private Color color;

    public Triangle(Point a, Point b, Point c, Color color){
        this.vertexA = a;
        this.vertexB = b;
        this.vertexC = c;
        this.color = color;
    }

    public Point getVertexA(){
        return this.vertexA;
    }

    public Point getVertexB(){
        return this.vertexB;
    }

    public Point getVertexC(){
        return this.vertexC;
    }

    public boolean equals(Object other){
        if (other != null){
            if(other.getClass().equals(Triangle.class)){
                if (((Triangle)other).getVertexA() == this.getVertexA() &&
                        ((Triangle)other).getVertexB() == this.getVertexB() &&
                        ((Triangle)other).getVertexC() == this.getVertexC() &&
                        ((Triangle)other).getColor() == this.getColor()){
                    return true;
                }
            }
        }
        return false;
    }

    public Color getColor() {
        return this.color;
    }


    public void setColor(Color c) {
        this.color = c;
    }


    public double getArea() {
        double a = getDistance(this.vertexA, this.vertexB);
        double b = getDistance(this.vertexB, this.vertexC);
        double c = getDistance(this.vertexC, this.vertexA);
        double s = (a+b+c)/2.0;
        double area = Math.sqrt(s*(s-a)*(s-b)*(s-c));
        return area;
    }


    public double getPerimeter() {
        double a = getDistance(this.vertexA, this.vertexB);
        double b = getDistance(this.vertexB, this.vertexC);
        double c = getDistance(this.vertexC, this.vertexA);
        return a+b+c;
    }

    public void translate(Point p) {
        double x1 = p.getX();
        double y1 = p.getY();

        double xa = vertexA.getX();
        double ya = vertexA.getY();

        double xb = vertexB.getX();
        double yb = vertexB.getY();

        double xc = vertexC.getX();
        double yc = vertexC.getY();

        Point newA = new Point((int)(xa +x1), (int)(ya + y1));
        Point newB = new Point((int)(xb +x1), (int)(yb + y1));
        Point newC = new Point((int)(xc +x1), (int)(yc + y1));

        this.vertexA = newA;
        this.vertexB = newB;
        this.vertexC = newC;
    }

    private double getDistance(Point p1, Point p2){
        double x1 = p1.getX();
        double y1 = p1.getY();

        double x2 = p2.getX();
        double y2 = p2.getY();

        double x_val = Math.pow(x1-x2,2.0);
        double y_val = Math.pow(y1-y2, 2.0);

        double distance = Math.sqrt(x_val+y_val);
        return distance;
    }
}
