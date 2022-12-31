package com.learnjava.concurrency.threadcongestion;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ThreadCongestionExample {
  public static void main(String[] args) {
    int objectsToProduce = 1_000_000;
    BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(objectsToProduce);
    ConsumerRunnable[] consumerRunnables = new ConsumerRunnable[3];

    synchronized(ThreadCongestionExample.class) {
      for (int i = 0; i < consumerRunnables.length; i++) {
        consumerRunnables[i] = new ConsumerRunnable(blockingQueue);
        Thread consumingThread = new Thread(consumerRunnables[i]);
        consumingThread.start();
      }
    }

    Thread producingThread = new Thread(() -> {
      for (int i = 0; i < objectsToProduce; i++) {
        try {
          blockingQueue.put("" + i);
        } catch (InterruptedException ex) {
          ex.printStackTrace();
        }
      }
      System.out.println("All objects produced");
      synchronized(ThreadCongestionExample.class) {
        for (int i = 0; i < consumerRunnables.length; i++) {
          consumerRunnables[i].stop();
        }
      }
    });
    producingThread.start();
  }
}
