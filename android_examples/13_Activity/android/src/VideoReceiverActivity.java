package org.qtproject.example;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import org.qtproject.example.ForegroundServiceLauncher;

import java.io.*;

import android.widget.Toast;

public class VideoReceiverActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

         System.out.println("Iniciando recebimento dos pacotes de vídeo.");
        //setContentView(R.layout.second_activity);
        //Toast.makeText(this, "RKRESSSIN", Toast.LENGTH_LONG).show();
        //startStream();

        System.out.println("Iniciando BlockingQueue!");

        try{
            BlockingQueueVideoPackets.main(null);
        } catch(Exception e){
            System.out.println("BlockingQueueVideoPackets: main exception");
        }
    }

    protected void onDestroy() {
        System.out.println("onDestroy Activity");
        super.onDestroy();
        //stopStream();
    }

    public void startStream() {
        ForegroundServiceLauncher goingOnForever = ForegroundServiceLauncher.getInstance();
        goingOnForever.startService(this);
    }
    public void stopStream() {
        ForegroundServiceLauncher goingOnForever = ForegroundServiceLauncher.getInstance();
        goingOnForever.stopService(this);
    }

}
