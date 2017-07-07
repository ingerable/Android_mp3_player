package jules.android_mp3_player.model;

/**
 * Created by jules97 on 03/07/2017.
 */

public class Song {

    private String title;
    private String artist;
    private long id;
    private String path;


    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Song(long id, String title, String artist, String path){
        this.title = title;
        this.id = id;
        this.artist = artist;
        this.path = path;
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
