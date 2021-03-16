//package org.qtproject.example;

//import java.io.IOException;
//import java.io.PrintStream;
//import java.net.Socket;
//import java.util.Scanner;
//import java.util.concurrent.BlockingQueue;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Future;

//import java.util.concurrent.ArrayBlockingQueue;
//import java.util.concurrent.BlockingQueue;
//import java.net.DatagramPacket;
//import java.net.DatagramSocket;
//import java.net.InetAddress;
//import java.io.IOException;
//import java.net.SocketException;
//import java.net.UnknownHostException;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import 	android.os.Process;

//public class VideoConsumerTask implements Runnable {

//    private BlockingQueue<byte[]> filaPacotesVideo;
//    private InetAddress serverAddr;
//    DatagramSocket udpSocket;
//    private int port = 5000;


//   public VideoConsumerTask(BlockingQueue<byte[]> filaPacotesVideo) {
//        this.filaPacotesVideo = filaPacotesVideo;
//        try{
//            this.serverAddr = InetAddress.getByName("192.168.1.183");
//        } catch(UnknownHostException e){

//       }
//    }

//        @Override
//        public void run() {
//            android.os.Process.setThreadPriority(-20);
//              try{
//              udpSocket = new DatagramSocket();
//              DatagramPacket packetSend = new DatagramPacket(h264s, h264s.length, serverAddr, 5600);
//              udpSocket.setBroadcast(true);
//              udpSocket.send(packetSend);
//              System.out.println("Consumindo pacote de vídeo!");
//            } catch(IOException e){
//              System.out.println("socket send: IOException");
//            } finally{
//              //udpSocket.close();
//              System.out.println("Socket fechado");
//            }
//            }
//}

