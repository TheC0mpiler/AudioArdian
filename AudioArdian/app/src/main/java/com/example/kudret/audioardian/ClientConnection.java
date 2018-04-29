package com.example.kudret.audioardian;



import android.os.StrictMode;

import com.example.kudret.audioardian.MetaServer.IMetaServer;
import com.example.kudret.audioardian.MetaServer.IMetaServerPrx;
import com.example.kudret.audioardian.MetaServer.Song;

/**
 * Created by kudret on 11/04/18.
 */

public class ClientConnection {
    String metaServerAdress;
    String metaServerPort;
    public ClientConnection(String metaServerAdress,String metaServerPort){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        this.metaServerPort = metaServerPort;
        this.metaServerAdress = metaServerAdress;
    }
    public void startStreaming(String name, String author, String album,int time){
        try(com.zeroc.Ice.Communicator communicator = com.zeroc.Ice.Util.initialize())
        {
            com.zeroc.Ice.ObjectPrx base = communicator.stringToProxy("MetaServer:default -h "+metaServerAdress+" -p "+metaServerPort);
            IMetaServerPrx metaServer = IMetaServerPrx.checkedCast(base);
            if(metaServer == null) {
                throw new Error("Invalid proxy");
            }
            metaServer.startStreaming(name,author,album,time);
        }
    }
    public Song[] getMusics(String name, String author, String album){
        try(com.zeroc.Ice.Communicator communicator = com.zeroc.Ice.Util.initialize())
        {
            com.zeroc.Ice.ObjectPrx base = communicator.stringToProxy("MetaServer:default -h "+metaServerAdress+" -p "+metaServerPort);
            IMetaServerPrx metaServer = IMetaServerPrx.checkedCast(base);
            if(metaServer == null) {
                throw new Error("Invalid proxy");
            }
            return metaServer.searchMusic(name,author,album);
        }
    }
}
