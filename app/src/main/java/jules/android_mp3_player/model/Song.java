package jules.android_mp3_player.model;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import java.util.ArrayList;

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

    public static ArrayList<Song> getSongList(Context context)
    {
        ArrayList<Song> songList = new ArrayList<Song>();
        ContentResolver musicResolver = context.getContentResolver();
        Uri musicUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String sortOrder = MediaStore.Audio.Media.TITLE + " ASC";
        Cursor musicCursor = musicResolver.query(musicUri, null, null, null, sortOrder);

        if(musicCursor!=null && musicCursor.moveToFirst()){
            //retrieve the data we want
            int titleColumn = musicCursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
            int idColumn = musicCursor.getColumnIndex(MediaStore.Audio.Media._ID);
            int artistColumn = musicCursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
            int dataColumn = musicCursor.getColumnIndex(MediaStore.Audio.Media.DATA);

            do{
                //instantiate a song and store it into the songs list
                long currentId = musicCursor.getLong(idColumn);
                String currentTitle = musicCursor.getString(titleColumn);
                String currentArtist = musicCursor.getString(artistColumn);
                String path = musicCursor.getString(dataColumn);
                songList.add(new Song(currentId, currentTitle, currentArtist,path));
            }
            while (musicCursor.moveToNext());
        }

        return songList;
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
