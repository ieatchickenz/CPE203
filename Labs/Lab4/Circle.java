import java.awt.*;
import java.lang.Math;

public class Circle implements Shape{
    private double radius;
    private Point center;
    private Color color;


    public Circle(double var1, Point var2, Color color) {
        this.radius = var1;
        this.center = var2;
        this.color = color;
    }

    public double getRadius() {
        return this.radius;
    }

    public void setRadius(double radius){
        this.radius = radius;
    }

    public Point getCenter() {
        return this.center;
    }

    public double getPerimeter() {
        double var1 = 2.0 * this.radius * Math.PI;
        return var1;
    }

    public Color getColor() {
        return this.color;
    }

    public void setColor(Color c) {
        this.color = c;
    }

    public double getArea() {
        return Math.pow(this.radius, 2) * Math.PI;
    }

    public void translate(Point p) {
        double x1 = p.getX();
        double y1 = p.getY();
        double x2 = this.center.getX();
        double y2 = this.center.getY();
        int newX = (int)(x1 + x2);
        int newY = (int)(y1 + y2);
        Point newCenter = new Point(newX, newY);
        this.center = newCenter;
    }

    public boolean equals(Object other) {
        if (other != null){
            if(other.getClass().equals(Circle.class)){
                if (((Circle)other).getRadius() == this.getRadius() &&
                        ((Circle)other).getCenter() == this.getCenter() &&
                        ((Circle)other).getColor() == this.getColor()){
                    return true;
                }
            }
        }
        return false;
    }
}

