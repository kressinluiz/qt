package org.qtproject.example;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueProducer implements Runnable{

    protected BlockingQueue queue = null;

    public BlockingQueueProducer(BlockingQueue queue) {
        this.queue = queue;
    }

    public void run() {
        try {
            queue.put("1");
            Thread.sleep(3);
            queue.put("2");
            Thread.sleep(3);
            queue.put("3");
            Thread.sleep(3);
            queue.put("4");
            Thread.sleep(3);
            queue.put("5");
            Thread.sleep(3);
            queue.put("6");
            Thread.sleep(3);
            queue.put("7");
            Thread.sleep(3);
            queue.put("8");
            Thread.sleep(3);
            queue.put("9");
            Thread.sleep(3);
            queue.put("10");
            Thread.sleep(3);
            queue.put("11");
            Thread.sleep(3);
            queue.put("12");
            Thread.sleep(3);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
