public class ActionFactory {


    public static ActionI createAnimationAction(EntityAction entity, WorldModel world, ImageStore imageStore, int repeatCount) {
        return new Animation(entity, world, imageStore, repeatCount);
    }

    public static ActionI createActivityAction(EntityAction entity, WorldModel world,
                                              ImageStore imageStore) {
        return new Activity(entity, world, imageStore);
    }
}
