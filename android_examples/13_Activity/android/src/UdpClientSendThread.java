package org.qtproject.example;

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
import java.net.UnknownHostException;
import java.net.SocketTimeoutException;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.qtproject.example.DataFlairService;

import java.io.*;

public class UdpClientSendThread implements Runnable {

        private Thread t;

        protected BlockingQueue<byte[]> queue = null;

        public UdpClientSendThread(BlockingQueue<byte[]> queue) {
            this.queue = queue;
        }

        private int port = 5001;

        @Override
        public void run() {
            boolean run = true;
            try{
                DatagramSocket udpSocket = new DatagramSocket(port);
                InetAddress serverAddr = InetAddress.getByName("192.168.1.183");
                byte [] buf = ("STRING ENVIADA COMO ARRAY DE BYTES!").getBytes();
                //try{
                    //byte[] buf = queue.take();
                    DatagramPacket packetSend = new DatagramPacket(buf, buf.length, serverAddr, port);
                    System.out.println("UDP Client : PORT " + String.valueOf(port));
                    System.out.println("UDP Client: Server Address: " + serverAddr);
                    System.out.println("UDP Client: Pronto para enviar!");

                    while(true)
                    {
                        System.out.println("Enviando pacote de dados...");
                        try{
                            udpSocket.send(packetSend);
                            System.out.println("Pacote de dados enviado.");
                        } catch(IOException e){
                            System.out.println("UDPClient send: IOException " + e);
                        }
                    }
                //}// catch(InterruptedException e){
                 //   System.out.println("InterruptedException!");
                //}

            } catch (SocketException e){
                System.out.println("UDP Client: SocketException " + e);
            } catch (SecurityException e) {
                System.out.println("UDP Client: SecurityException " + e);
            } catch (UnknownHostException e) {
                System.out.println("UDP Client: UnknownHostException " + e);
            }

            public void start () {
                System.out.println("Starting Udp Thread");


                if (t == null) {
                    t = new Thread (this, "udpThread");
                    t.start();
                }
            }

        }


}
