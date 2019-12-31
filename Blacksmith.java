import processing.core.PImage;

import java.util.List;

public class Blacksmith implements EntityI {

    private Point position;
    private List<PImage> images;
    private int imageIndex;


    public Blacksmith(String id, Point position,
                      List<PImage> images, int resourceLimit, int resourceCount,
                      int actionPeriod, int animationPeriod){
        this.position = position;
        this.images = images;
    }


    public Point getPosition(){
        return this.position;
    }

    public void setPosition(Point point) {
        this.position = point;
    }

    public void nextImage() {
        this.imageIndex = (this.imageIndex + 1) % this.images.size();
    }

    public PImage getCurrentImage() {
        return images.get(imageIndex);
    }
}
