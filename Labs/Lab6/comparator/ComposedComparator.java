import java.util.Comparator;

public class ComposedComparator {
    Comparator<Song> c1;
    Comparator<Song> c2;

    public ComposedComparator(Comparator<Song> c1, Comparator<Song> c2){
        this.c1 = c1;
        this.c2 = c2;
    }

    public int compare(Song one, Song two){
        if(c1.compare(one, two) == 0){
            return c2.compare(one, two);
        }
        return c1.compare(one, two);
    }
    

}
