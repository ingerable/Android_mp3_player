package jules.android_mp3_player.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import jules.android_mp3_player.Activities.MainActivity;
import jules.android_mp3_player.Activities.PlaylistsActivity;
import jules.android_mp3_player.R;

public class SettingsActivity extends AppCompatActivity {

    float x1,x2; //position of the finger at the beginning and at the end of the gesture

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        TextView txtViewSongs = (TextView) findViewById(R.id.textView_Songs);
        TextView txtViewPlaylist = (TextView) findViewById(R.id.textView_Playlist);

        txtViewSongs.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                changeToSongs();
            }
        });

        txtViewPlaylist.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                changeToPlaylist();
            }
        });
    }

    public void changeToSongs(){
        Intent intent = new Intent(this, MainActivity.class);
        finish();
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
    }

    public void changeToPlaylist(){
        Intent intent = new Intent(this, PlaylistsActivity.class);
        finish();
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }
}
