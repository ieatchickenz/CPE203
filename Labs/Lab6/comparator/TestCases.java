import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Comparator;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.Before;

public class TestCases
{
   private static final Song[] songs = new Song[] {
         new Song("Decemberists", "The Mariner's Revenge Song", 2005),
         new Song("Rogue Wave", "Love's Lost Guarantee", 2005),
         new Song("Avett Brothers", "Talk on Indolence", 2006),
         new Song("Gerry Rafferty", "Baker Street", 1998),
         new Song("City and Colour", "Sleeping Sickness", 2007),
         new Song("Foo Fighters", "Baker Street", 1997),
         new Song("Queen", "Bohemian Rhapsody", 1975),
         new Song("Gerry Rafferty", "Baker Street", 1978)
      };

   @Test
   public void testArtistComparator()
   {
       Song[] song_test = new Song[] {
         new Song("Decemberists", "The Mariner's Revenge Song", 2005),
         new Song("Rogue Wave", "Love's Lost Guarantee", 2005),
         new Song("Avett Brothers", "Talk on Indolence", 2006),
         new Song("Gerry Rafferty", "Baker Street", 1998),
         new Song("City and Colour", "Sleeping Sickness", 2007),
         new Song("Foo Fighters", "Baker Street", 1997),
         new Song("Queen", "Bohemian Rhapsody", 1975),
         new Song("Gerry Rafferty", "Baker Street", 1978),
         new Song("Queen", "We Will Rock You", 1977)
         };

         ArtistComparator comparator = new ArtistComparator();
         assertTrue(comparator.compare(song_test[0],song_test[1]) < 0);
         assertTrue(comparator.compare(song_test[0],song_test[2]) > 0);
         assertTrue(comparator.compare(song_test[2],song_test[4]) < 0);
         assertTrue(comparator.compare(song_test[7],song_test[7]) == 0);
         assertTrue(comparator.compare(song_test[6],song_test[8]) == 0);
         assertTrue(comparator.compare(song_test[2],song_test[3]) < 0);
         assertTrue(comparator.compare(song_test[1],song_test[2]) > 0);
   }

   @Test
   public void testLambdaTitleComparator()
   {
      Comparator<Song> titleCompare = (songOne, songTwo) -> songOne.getTitle().compareTo(songTwo.getTitle());

     assertTrue(titleCompare.compare(songs[0],songs[1]) > 0);
     assertTrue(titleCompare.compare(songs[0],songs[2]) > 0);
     assertTrue(titleCompare.compare(songs[2],songs[0]) < 0);
     assertTrue(titleCompare.compare(songs[5],songs[7]) == 0);
     assertTrue(titleCompare.compare(songs[6],songs[7]) > 0);
     assertTrue(titleCompare.compare(songs[2],songs[3]) > 0);
     assertTrue(titleCompare.compare(songs[1],songs[2]) < 0);
   }

   @Test
   public void testYearExtractorComparator()
   {
       Comparator<Song> keyExtractor = Comparator.comparingInt(Song::getYear).reversed();
       assertTrue(keyExtractor.compare(songs[0],songs[1]) ==  0);
       assertTrue(keyExtractor.compare(songs[0],songs[2]) > 0);
       assertTrue(keyExtractor.compare(songs[2],songs[0]) < 0);
       assertTrue(keyExtractor.compare(songs[5],songs[7]) < 0);
       assertTrue(keyExtractor.compare(songs[6],songs[7]) > 0);
       assertTrue(keyExtractor.compare(songs[2],songs[3]) < 0);
       assertTrue(keyExtractor.compare(songs[1],songs[2]) > 0);
   }

   @Test
   public void testComposedComparator()
   {
       Comparator<Song> keyExtractor = Comparator.comparingInt(Song::getYear).reversed();
        ArtistComparator comparator = new ArtistComparator();
       ComposedComparator composed = new ComposedComparator(comparator, keyExtractor);
       assertTrue(composed.compare(songs[3], songs[7]) < 0);
   }

   @Test
   public void testThenComparing()
   {
       Comparator<Song> comp1 = (s1,s2) -> s1.getTitle().compareTo(s2.getTitle());
       Comparator<Song> comp2 = Comparator.comparing(Song::getArtist);
       Comparator<Song> comp3 = comp1.thenComparing(comp2);
       assertTrue(comp3.compare(songs[3], songs[5]) > 0);
   }

   @Test
   public void runSort()
   {
      List<Song> songList = new ArrayList<>(Arrays.asList(songs));
      List<Song> expectedList = Arrays.asList(
         new Song("Avett Brothers", "Talk on Indolence", 2006),
         new Song("City and Colour", "Sleeping Sickness", 2007),
         new Song("Decemberists", "The Mariner's Revenge Song", 2005),
         new Song("Foo Fighters", "Baker Street", 1997),
         new Song("Gerry Rafferty", "Baker Street", 1978),
         new Song("Gerry Rafferty", "Baker Street", 1998),
         new Song("Queen", "Bohemian Rhapsody", 1975),
         new Song("Rogue Wave", "Love's Lost Guarantee", 2005)
         );
        Comparator<Song> comp1 = (s1,s2) -> s1.getArtist().compareTo(s2.getArtist());
        Comparator<Song> comp2 = Comparator.comparing(Song::getTitle);
        Comparator<Song> comp3 = Comparator.comparingInt(Song::getYear);
        Comparator<Song> sorting = comp1.thenComparing(comp2).thenComparing(comp3);
      songList.sort(sorting);

      assertEquals(songList, expectedList);
   }
  
}

