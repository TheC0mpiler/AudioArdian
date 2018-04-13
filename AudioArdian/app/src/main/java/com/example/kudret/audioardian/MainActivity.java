package com.example.kudret.audioardian;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.kudret.audioardian.MetaServer.Song;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    String metaServerAdress;
    String metaServerPort;

    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout layout = new LinearLayout(MainActivity.this);
        layout.setOrientation(LinearLayout.VERTICAL);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Server ip");
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        layout.addView(input);

        final EditText input1 = new EditText(this);
        input1.setInputType(InputType.TYPE_CLASS_TEXT);
        layout.addView(input1);

        builder.setView(layout);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                metaServerAdress = input.getText().toString();
                metaServerPort = input1.getText().toString();
                ClientConnection a = new ClientConnection(metaServerAdress,metaServerPort);
                mListView = findViewById(R.id.listView);
                Song [] allMusic = a.getAllMusics();
                List<Song> songs = generateSongs(allMusic);
                SongAdapter adapter = new SongAdapter(MainActivity.this, songs);
                mListView.setAdapter(adapter);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }
    private List<Song> generateSongs(Song[] songList){
        List<Song> songs = new ArrayList<Song>();
        for(int i = 0 ; i < songList.length ; i++){
            songs.add(songList[i]);
        }
        return songs;
    }

}
