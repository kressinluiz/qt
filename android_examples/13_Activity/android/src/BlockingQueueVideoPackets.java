package org.qtproject.example;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public class BlockingQueueVideoPackets {

    public static void main(String[] args) throws Exception {

        final int MAX_PRIORITY = 10;
        final int NORM_PRIORITY = 5;

        ExecutorService executorService = Executors.newSingleThreadExecutor();

            executorService.execute(new Runnable() {
                public void run() {
                    System.out.println("Asynchronous task");
                    BlockingQueue queue = new ArrayBlockingQueue(1024);

                    BlockingQueueProducer producer = new BlockingQueueProducer(queue);
                    BlockingQueueConsumer consumer = new BlockingQueueConsumer(queue);

                    Thread t1 = new Thread(producer);
                    Thread t2 = new Thread(consumer);

                    t1.setPriority(MAX_PRIORITY);
                    t2.setPriority(NORM_PRIORITY);

                    t1.start();
                    t2.start();
                }
            });
    }
}
