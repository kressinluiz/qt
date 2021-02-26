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
import java.net.UnknownHostException;
import java.net.SocketTimeoutException;

import java.io.*;

public class UdpClientSendThread implements Runnable {

        private int port = 5001;

        @Override
        public void run() {
            boolean run = true;
            try{
                DatagramSocket udpSocket = new DatagramSocket(port);
                InetAddress serverAddr = InetAddress.getByName(null);
                byte [] buf = ("STRING ENVIADA COMO ARRAY DE BYTES!").getBytes();
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
                        run = true;

                        System.out.println("Iniciando Server.");
                        while (run) {
                            try {
                                byte[] message = new byte[8000];
                                DatagramPacket packetReceive = new DatagramPacket(message,message.length);
                                System.out.println("Aguardando dados...");
                                udpSocket.setSoTimeout(10);
                                udpSocket.receive(packetReceive);
                                String text = new String(message, 0, packetReceive.getLength());
                                System.out.println("Received text: " + text);
                            } catch (SocketTimeoutException e) {
                                System.out.println("UDP Server: Timeout Exception");
                                run = false;
                                //udpSocket.close();
                           } catch (IOException e) {
                                System.out.println(" UDP Server: IOException");
                                run = false;
                                //udpSocket.close();
                            }
                        }
                    } catch(IOException e){
                        System.out.println("UDPClient send: IOException " + e);
                    }
                }

            } catch (SocketException e){
                System.out.println("UDP Client: SocketException " + e);
            } catch (SecurityException e) {
                System.out.println("UDP Client: SecurityException " + e);
            } catch (UnknownHostException e) {
                System.out.println("UDP Client: UnknownHostException " + e);
            }

        }


}
