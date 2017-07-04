package jules.android_mp3_player;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by jules97 on 04/07/2017.
 */

public class SongAdapter extends ArrayAdapter<Song> {

    public SongAdapter(Context context, List<Song> songs){
        super(context,0,songs);
    }

    public View getView(int position, View convertView, ViewGroup parent)
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

        return convertView;
    }
}
