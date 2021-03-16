package org.qtproject.example;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import org.qtproject.example.Data;
import org.qtproject.example.Receiver;
import org.qtproject.example.Sender;


public class ActivityThread extends Activity{

    Data data = new Data();
    Thread sender = new Thread(new Sender(data));
    Thread receiver  = new Thread(new Receiver(data));

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        System.out.println("HERE WE GO!");

        sender.start();
        receiver.start();
    }

    @Override
    public void onStop() {
        System.out.println("onStop Activity");
        super.onStop();
    }

    protected void onDestroy() {
        System.out.println("onDestroy Activity");
        super.onDestroy();
    }


}
