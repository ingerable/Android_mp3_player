package jules.android_mp3_player.adapters;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.util.List;

import jules.android_mp3_player.R;
import jules.android_mp3_player.model.MyMediaPlayer;
import jules.android_mp3_player.model.Song;

/**
 * Created by jules97 on 04/07/2017.
 */

public class SongAdapter extends ArrayAdapter<Song> {


    public SongAdapter(Context context, List<Song> songs){
        super(context,0,songs);
    }

    public View getView(int position,View convertView, ViewGroup parent)
    {
        if(convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_song,parent,false);
        }
        SongViewHolder viewHolder = (SongViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new SongViewHolder();
            viewHolder.title = (TextView) convertView.findViewById(R.id.title);
            viewHolder.artist = (TextView) convertView.findViewById(R.id.artist);
            convertView.setTag(viewHolder);
        }

        Song song = getItem(position);

        viewHolder.title.setText(song.getTitle());
        viewHolder.artist.setText(song.getArtist());

        TextView txt = (TextView) convertView.findViewById(R.id.title); // retrieve the textView

        final Song songClicked = song; // have to declare this variable as final so i can use it freely in my event

        /* on click event for the textview */
        txt.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                playSelectedSong(songClicked);
            }
        });

        return convertView;
    }

    // read the song s with the sdCard Path
    public void playSelectedSong(Song s)
    {
        if(s.getPath()!=null)
        {
            try {
                MediaPlayer mp = MyMediaPlayer.getMyMediaPlayer(); // get the mediaPlayer
                mp.reset();// we are using the same media player, so we have to call reset() to put the mediaPlayer in the iddle State otherwise setDataSource() will throw an error
                String fullPath= "file:///"+s.getPath(); // building absolute path
                mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mp.setDataSource(fullPath);
                mp.prepare();
                mp.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
