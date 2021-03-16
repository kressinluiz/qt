package org.qtproject.example;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.io.*;

import android.widget.Toast;

public class VideoReceiveAndSendActivity extends Activity {

    VideoProducer videoProducer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //ForegroundServiceLauncher goingOnForever = ForegroundServiceLauncher.getInstance();
        //goingOnForever.startService(this);
        System.out.println("onCreate");
         finish();
       }


    @Override
    public void onStart(){
        super.onStart();
        System.out.println("onStart");
//        if(videoProducer != null){
//            videoProducer.stop();
//            videoProducer = null;
//        }
        //videoProducer = new VideoProducer(this);
        //videoProducer.start();
    }

    @Override
    public void onResume(){
        super.onResume();
        System.out.println("onResume");
    }

    @Override
    public void onPause(){
        super.onPause();
        System.out.println("onPause");
    }

    @Override
    public void onStop() {
        System.out.println("onStop Activity");
        super.onStop();
        //videoProducer.stop();
    }

    protected void onDestroy() {
        System.out.println("onDestroy Activity");
        super.onDestroy();
        //videoProducer.destroy();

    }

}
