package jules.android_mp3_player;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class PlaylistsActivity extends AppCompatActivity {

    private float x1,x2; //position of the finger at the beginning and at the end of the gesture

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlists);

        //get the TextView object to add onClick listener
        TextView txtViewSongs = (TextView) findViewById(R.id.textView_Songs);
        TextView txtViewSettings = (TextView) findViewById(R.id.textView_Settings);

        txtViewSongs.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                changeToSongs();
            }
        });

        txtViewSettings.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                changeToSettings();
            }
        });
    }


   //function used to proceed to the transition between 2 activity
    public void changeToSongs(){
        Intent intent = new Intent(this, MainActivity.class);
        finish();
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }

    public void changeToSettings(){
        Intent intent = new Intent(this, SettingsActivity.class);
        finish();
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
    }

}
