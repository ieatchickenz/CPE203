import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class WorkSpace {
    private List<Shape> shapes;

    public WorkSpace(){
        this.shapes = new ArrayList<Shape>();
    }

    public void add(Shape shape){
        shapes.add(shape);
    }

    public Shape get(int index){
        return shapes.get(index);
    }

    public int size(){
        return shapes.size();
    }

    public List<Circle> getCircles(){
        List<Circle> circles = new ArrayList<>();
        for(Shape shape : shapes){
            if(shape instanceof Circle){
                circles.add((Circle)shape);
            }
        }
        return circles;
    }

    public List<Rectangle> getRectangles(){
        List<Rectangle> rectangles = new ArrayList<>();
        for(Shape shape : shapes){
            if(shape instanceof Rectangle){
                rectangles.add((Rectangle)shape);
            }
        }
        return rectangles;
    }

    public List<Triangle> getTriangles(){
        List<Triangle> triangles = new ArrayList<>();
        for(Shape shape : shapes){
            if(shape instanceof Triangle){
                triangles.add((Triangle)shape);
            }
        }
        return triangles;
    }

    public List<Shape> getShapesByColor(Color color){
        List<Shape> colors = new ArrayList<>();
        for(Shape shape : shapes){
            if (shape.getColor().equals(color)){
                colors.add(shape);
            }
        }
        return colors;
    }

    public double getAreaOfAllShapes(){
        double finalArea = 0;
        for(Shape shape : shapes){
            finalArea += shape.getArea();
        }
        return finalArea;
    }

    double getPerimeterOfAllShapes(){
        double finalPerimeter = 0;
        for(Shape shape : shapes){
            finalPerimeter += shape.getPerimeter();
        }
        return finalPerimeter;
    }

}
