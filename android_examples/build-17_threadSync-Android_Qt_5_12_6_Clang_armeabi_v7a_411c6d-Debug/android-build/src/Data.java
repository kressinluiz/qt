package org.qtproject.example;

public class Data {
    private String packet;

       // True if receiver should wait
       // False if sender should wait
       private boolean transfer = true;

       public synchronized void send(String packet) {
           while (!transfer) {
               try {
                   wait();
               } catch (InterruptedException e)  {
                   Thread.currentThread().interrupt();
                   System.out.println("thread interrupted");
               }
           }
           transfer = false;

           this.packet = packet;
           notifyAll();
       }

       public synchronized String receive() {
           while (transfer) {
               try {
                   wait();
               } catch (InterruptedException e)  {
                   Thread.currentThread().interrupt();
                   System.out.println("thread interrupted");
               }
           }
           transfer = true;

           notifyAll();
           return packet;
       }
}
