public interface MoveEntity extends AnimateEntity {
    boolean moveTo(WorldModel world,
                   EntityI target, EventScheduler scheduler);
    Point nextPosition(WorldModel world, Point destPos);
}
