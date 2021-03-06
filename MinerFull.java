import processing.core.PImage;

import java.util.List;
import java.util.Optional;

public class MinerFull implements MoveEntity{
    private String id;
    private Point position;
    private List<PImage> images;
    private int imageIndex;
    private int resourceLimit;
    private int actionPeriod;
    private int animationPeriod;

    public MinerFull(String id, Point position,
                    List<PImage> images, int resourceLimit, int resourceCount,
                    int actionPeriod, int animationPeriod){
        this.id = id;
        this.position = position;
        this.images = images;
        this.resourceLimit = resourceLimit;
        this.actionPeriod = actionPeriod;
        this.animationPeriod = animationPeriod;
    }


    public boolean moveTo(WorldModel world, EntityI target, EventScheduler scheduler) {
        if (Point.adjacent(this.position, target.getPosition()))
        {
            return true;
        }
        else
        {
            Point nextPos = this.nextPosition(world, target.getPosition());

            if (!this.position.equals(nextPos))
            {
                Optional<EntityI> occupant = world.getOccupant(nextPos);
                if (occupant.isPresent())
                {
                    scheduler.unscheduleAllEvents(occupant.get());
                }

                world.moveEntity(this, nextPos);
            }
            return false;
        }
    }


    public Point nextPosition(WorldModel world, Point destPos) {
        int horiz = Integer.signum(destPos.x - this.position.x);
        Point newPos = new Point(this.position.x + horiz,
                this.position.y);

        if (horiz == 0 || world.isOccupied(newPos))
        {
            int vert = Integer.signum(destPos.y - this.position.y);
            newPos = new Point(this.position.x,
                    this.position.y + vert);

            if (vert == 0 || world.isOccupied(newPos))
            {
                newPos = this.position;
            }
        }

        return newPos;
    }


    public int getAnimationPeriod() {
        return this.animationPeriod;
    }


    public void scheduleAction(EventScheduler scheduler, WorldModel world, ImageStore imageStore) {
        scheduler.scheduleEvent(this,
                ActionFactory.createActivityAction(this, world, imageStore),
                this.actionPeriod);
        scheduler.scheduleEvent(this, ActionFactory.createAnimationAction(this, world, imageStore,
                                0),
                this.getAnimationPeriod());
    }


    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {
        Optional<EntityI> fullTarget = world.findNearest(this.position,
                Blacksmith.class);

        if (fullTarget.isPresent() &&
                this.moveTo(world, fullTarget.get(), scheduler))
        {
            this.transform(world, scheduler, imageStore);
        }
        else
        {
            scheduler.scheduleEvent(this,
                    ActionFactory.createActivityAction(this, world, imageStore),
                    this.actionPeriod);
        }
    }

    public Point getPosition() {
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

    public void transform(WorldModel world, EventScheduler scheduler, ImageStore imageStore)
    {
        MinerNotFull miner = EntityFactory.createMinerNotFull(this.id, this.resourceLimit,
                this.position, this.actionPeriod, this.animationPeriod,
                this.images);

        world.removeEntity(this);
        scheduler.unscheduleAllEvents(this);

        world.addEntity(miner);
        miner.scheduleAction(scheduler, world, imageStore);
    }
}
