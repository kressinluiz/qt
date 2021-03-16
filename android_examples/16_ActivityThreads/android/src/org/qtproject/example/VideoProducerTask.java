//package org.qtproject.example;

//import java.io.IOException;
//import java.io.PrintStream;
//import java.net.Socket;
//import java.util.Scanner;
//import java.util.concurrent.BlockingQueue;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Future;
//import 	android.os.Process;

//public class VideoProducerTask implements Runnable {

//        BlockingQueue<byte[]> filaPacotesVideo;
//        private byte[]  h264;

//       public VideoProducerTask(BlockingQueue<byte[]> filaPacotesVideo, byte[] h264) {
//            this.filaPacotesVideo = filaPacotesVideo;
//            this.h264 = h264;
//        }

//        @Override
//        public void run() {
//            android.os.Process.setThreadPriority(-20);
//                try{
//                    System.out.println("Colocando pacote de vídeo");
//                    filaPacotesVideo.put(h264);
//                } catch(InterruptedException e) {

//                }
//        }

//}


