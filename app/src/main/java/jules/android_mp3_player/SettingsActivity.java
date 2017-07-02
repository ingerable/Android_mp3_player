package jules.android_mp3_player;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;

public class SettingsActivity extends AppCompatActivity {

    float x1,x2; //position of the finger at the beginning and at the end of the gesture

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    public boolean onTouchEvent(MotionEvent event){

        switch(event.getAction())
        {
            case MotionEvent.ACTION_DOWN: // gesture started
                x1 = event.getX();
                break;
            case MotionEvent.ACTION_UP: // gesture ended
                x2 = event.getX();
                float diff = x2-x1;
                if(Math.abs(diff)>Constants.MIN_DISTANCE) // check the minimum distance for the swipe
                {
                    if(diff<0) // swapping to song activity
                    {
                        Intent intent = new Intent(this, MainActivity.class);
                        finish();
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                    }else{ // swapping to playlists activity
                        Intent intent = new Intent(this, PlaylistsActivity.class);
                        finish();
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                    }

                }
                break;
        }
        return super.onTouchEvent(event);
    }
}
