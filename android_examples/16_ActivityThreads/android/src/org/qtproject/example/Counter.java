package org.qtproject.example;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Counter{

  private BlockingQueue<byte[]> filaPacotesVideo;
  InetAddress serverAddr;
  DatagramSocket udpSocket;
  private int port = 5000;

  public Counter(){
    this.filaPacotesVideo = new ArrayBlockingQueue<byte[]>(200, true);
    try {
        this.udpSocket = new DatagramSocket(port);
        this.serverAddr = InetAddress.getByName("192.168.1.183");
    } catch(SocketException e){

    } catch(UnknownHostException e){

    }
    System.out.println("new blockingqueue and socket");
  }

  private static volatile int count = 0;

  public synchronized void armazenaPacote(byte[] h264){

    //try{
        //filaPacotesVideo.put(h264);
        this.count = this.count + 1;
        System.out.println(String.valueOf(this.count));
    //} catch(InterruptedException e) {

    //}
  }

  public void transmitePacote (){
          try {
                  System.out.println("Entrando");
                  byte[] h264 = null;
                  while( (h264 = filaPacotesVideo.take()) != null) {
                          try{
                              DatagramPacket packetSend = new DatagramPacket(h264, h264.length, serverAddr, port);
                              udpSocket.send(packetSend);
                            System.out.println("Consumindo pacote de vídeo!");
                          } catch(IOException e){

                          }
                  }
              } catch (InterruptedException e) {
                      throw new RuntimeException(e);
              }
          //udpSocket.close() but we never get here...
          System.out.println("Fila vazia!");
  }

  public void stopCounter(){
    this.count = 0;
    udpSocket.close();
  }

}
