public class Animation implements ActionI{
    private EntityAction entity;
    private WorldModel world;
    private ImageStore imageStore;
    private int repeatCount;

    public Animation(EntityAction entity, WorldModel world,
                     ImageStore imageStore, int repeatCount){
        this.entity = entity;
        this.world = world;
        this.imageStore = imageStore;
        this.repeatCount = repeatCount;
    }


    public void executeAction(EventScheduler scheduler) {
        this.entity.nextImage();

        if (this.repeatCount != 1)
        {
            scheduler.scheduleEvent(this.entity,
                    ActionFactory.createAnimationAction(entity, world, imageStore,
                            Math.max(this.repeatCount - 1, 0)),
                    ((AnimateEntity)entity).getAnimationPeriod());
        }
    }
}
