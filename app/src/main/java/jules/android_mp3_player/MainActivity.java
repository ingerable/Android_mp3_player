package jules.android_mp3_player;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private float x1,x2; //position of the finger at the beginning and at the end of the gesture
    private ArrayList<Song> songList; // list of song object
    private ListView songView; // listview layout

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        songView = (ListView) findViewById(R.id.song_List); // initialize songView object with listView layout
        songList = new ArrayList<Song>();

        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);


        TextView txtViewSettings = (TextView) findViewById(R.id.textView_Settings);
        TextView txtViewPlaylist = (TextView) findViewById(R.id.textView_Playlist);

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
        Cursor musicCursor = musicResolver.query(musicUri, null, null, null, null);

        if(musicCursor!=null && musicCursor.moveToFirst()){
            int titleColumn = musicCursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
            int idColumn = musicCursor.getColumnIndex(MediaStore.Audio.Media._ID);
            int artistColumn = musicCursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);

            do{
                long currentId = musicCursor.getLong(idColumn);
                String currentTitle = musicCursor.getString(titleColumn);
                String currentArtist = musicCursor.getString(artistColumn);
                System.out.println(currentTitle);
                songList.add(new Song(currentId, currentTitle, currentArtist));
            }
            while (musicCursor.moveToNext());
        }
    }
}
