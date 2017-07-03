package jules.android_mp3_player;

/**
 * Created by jules97 on 03/07/2017.
 */

public class Song {

    private String title;
    private String artist;
    private long id;

    public Song(long id, String title, String artist){
        this.title = title;
        this.id = id;
        this.artist = artist;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
