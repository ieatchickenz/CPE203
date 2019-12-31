import processing.core.PImage;

public interface EntityI {
    Point getPosition();
    void setPosition(Point point);
    void nextImage();
    PImage getCurrentImage();
}
