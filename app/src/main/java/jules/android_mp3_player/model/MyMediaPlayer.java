package jules.android_mp3_player.model;

import android.media.MediaPlayer;

/**
 * Created by jules97 on 07/07/2017.
 */

public class MyMediaPlayer {

    public static MediaPlayer mp;

    public static MediaPlayer getMyMediaPlayer() {
        if(mp == null)
        {
            new MyMediaPlayer();
        }
        return mp;
    }

    private MyMediaPlayer() {

        mp = new MediaPlayer();
    }
}
