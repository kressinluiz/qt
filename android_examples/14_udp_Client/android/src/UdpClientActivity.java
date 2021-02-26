package org.kressin.udpclient;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;

import org.kressin.udpclient.UdpClientSendThread;

import java.io.*;


public class UdpClientActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        System.out.println("onCreate!");
        UdpClientSendThread c = new UdpClientSendThread();
        new Thread(c).start();
    }
}
