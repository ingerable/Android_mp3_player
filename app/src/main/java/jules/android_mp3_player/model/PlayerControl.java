package jules.android_mp3_player.model;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import jules.android_mp3_player.R;

/**
 * Created by jules97 on 12/01/2018.
 */

public class PlayerControl extends AppCompatActivity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_controls);
    }
}
