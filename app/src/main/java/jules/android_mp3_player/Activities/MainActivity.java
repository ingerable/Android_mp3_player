package jules.android_mp3_player.Activities;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import jules.android_mp3_player.R;
import jules.android_mp3_player.model.Song;
import jules.android_mp3_player.adapters.SongAdapter;

public class MainActivity extends AppCompatActivity {

    private float x1,x2; //position of the finger at the beginning and at the end of the gesture

    private ArrayList<Song> songList; // list of song object

    private SongAdapter adapter; // adapter for the song list


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView songView = (ListView) findViewById(R.id.song_List); // initialize songView object with listView layout
        songList = new ArrayList<Song>();
        getSongList(); // fill the list with songs
        adapter = new SongAdapter(MainActivity.this,songList); // initialize the adapter
        songView.setAdapter(adapter);

        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
        TextView txtViewSettings = (TextView) findViewById(R.id.textView_Settings);
        TextView txtViewPlaylist = (TextView) findViewById(R.id.textView_Playlist);
        final TextView txtViewTitle = (TextView) findViewById(R.id.title); // retrieve the title text view to add an on click event

        txtViewSettings.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                changeToSettings();
            }
        });

        txtViewPlaylist.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                changeToPlaylist();
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission granted and now can proceed
                    getSongList();

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(MainActivity.this, "Permission denied to read your External storage", Toast.LENGTH_SHORT).show();
                }
                return;
            }
            // add other cases for more permissions
        }
    }


    public void changeToSettings(){
        Intent intent = new Intent(this, SettingsActivity.class);
        finish();
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }

    public void changeToPlaylist(){
        Intent intent = new Intent(this, PlaylistsActivity.class);
        finish();
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
    }

    public void getSongList(){
        ContentResolver musicResolver = getContentResolver();
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
    }

}
