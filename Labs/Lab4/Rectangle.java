import java.awt.*;

public class Rectangle implements Shape{
    private double width;
    private double height;
    private Point topLeft;
    private Color color;


    public Rectangle(double width, double height, Point topLeft, Color c) {
        this.topLeft = topLeft;
        this.width = width;
        this.height = height;
        this.color = c;
    }

    public double getWidth(){
        return this.width;
    }

    public void setWidth(double width){
        this.width = width;
    }

    public double getHeight(){
        return this.height;
    }

    public void setHeight(double height){
        this.height = height;
    }

    public Point getTopLeft() {
        return this.topLeft;
    }

    public boolean equals(Object other) {
        if (other != null){
            if(other.getClass().equals(Rectangle.class)){
                if (((Rectangle)other).getWidth() == this.getWidth() &&
                        ((Rectangle)other).getHeight() == this.getHeight() &&
                        ((Rectangle)other).getColor() == this.getColor()){
                    return true;
                }
            }
        }
        return false;
    }

    public double getPerimeter() {
        return (2 * this.height) + (2 * this.width);
    }

    public Color getColor() {
        return this.color;
    }


    public void setColor(Color c) {
        this.color = c;
    }


    public double getArea() {
        return height * width;
    }


    public void translate(Point p) {
        double x1 = p.getX();
        double y1 = p.getY();
        double x2 = this.topLeft.getX();
        double y2 = this.topLeft.getY();
        int newX = (int)(x1 + x2);
        int newY = (int)(y1 + y2);
        Point newCorner = new Point(newX, newY);
        this.topLeft = newCorner;
    }
}
