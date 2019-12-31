import java.util.List;
import processing.core.PImage;

final class Background
{
    public String id;
    public List<PImage> images;
   public int imageIndex;

   public Background(String id, List<PImage> images)
   {
      this.id = id;
      this.images = images;
   }
//
//   public static PImage getCurrentImage(Background entity)
//   {
//      if (entity != null)
//      {
//         return (entity).images
//                 .get((entity).imageIndex);
//      }
//
//      else
//      {
//         throw new UnsupportedOperationException(
//                 String.format("getCurrentImage not supported for %s",
//                         entity));
//      }
//   }
}
