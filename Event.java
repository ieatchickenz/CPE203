final class Event
{
   private ActionI action;
   private long time;
   private EntityI entity;

   public Event(ActionI action, long time, EntityI entity)
   {
      this.action = action;
      this.time = time;
      this.entity = entity;
   }

   public ActionI getAction(){return this.action;}

   public long getTime(){return this.time;}

   public EntityI getEntity(){return this.entity;}

}
