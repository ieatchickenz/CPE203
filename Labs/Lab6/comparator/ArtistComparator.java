import java.util.Comparator;

public class ArtistComparator implements Comparator<Song> {
    public int compare(Song songOne, Song songTwo){
        String artistOne = songOne.getArtist();
        String artistTwo = songTwo.getArtist();
        return artistOne.compareTo(artistTwo);
    }

}
