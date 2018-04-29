package com.example.kudret.audioardian;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kudret.audioardian.MetaServer.Song;
import com.squareup.picasso.Picasso;
import com.zeroc.IceInternal.Ex;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

import static java.security.AccessController.getContext;

/**
 * Created by kudret on 13/04/18.
 */



public class SongAdapter extends ArrayAdapter<Song> {

    public SongAdapter(Context context, List<Song> songs) {
        super(context, 0, songs);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_song,parent, false);
        }

        SongViewHolder viewHolder = (SongViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new SongViewHolder();
            viewHolder.name = (TextView) convertView.findViewById(R.id.name);
            viewHolder.author = (TextView) convertView.findViewById(R.id.author);
            viewHolder.album = (TextView) convertView.findViewById(R.id.album);
            viewHolder.duration = (TextView) convertView.findViewById(R.id.duration);
            viewHolder.cover = (ImageView) convertView.findViewById(R.id.cover);
            convertView.setTag(viewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<Tweet> tweets
        Song music = getItem(position);

        //il ne reste plus qu'à remplir notre vue
        viewHolder.name.setText(music.name);
        viewHolder.author.setText(music.author);
        viewHolder.album.setText(music.album);
        int reste = music.duration%60;
        String duration = Integer.toString((music.duration - reste)/60) ;
        duration +=":";
        if(reste < 10 )
            duration +="0";
        duration += Integer.toString(reste);
        viewHolder.duration.setText(duration);
        Picasso.with(getContext()).load(music.cover).into(viewHolder.cover);



        return convertView;
    }

    private class SongViewHolder{
        public TextView name;
        public TextView author;
        public TextView album;
        public TextView duration;
        public ImageView cover;
    }
}
