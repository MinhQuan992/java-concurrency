package com.learnjava.concurrency.threadcongestion;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class ConsumerRunnable implements Runnable {
  private final BlockingQueue<String> blockingQueue;
  private AtomicBoolean keepRunning = new AtomicBoolean(true);

  public ConsumerRunnable(BlockingQueue<String> blockingQueue) {
    this.blockingQueue = blockingQueue;
  }

  public void stop() {
    System.out.println("Stopped thread");
    keepRunning.set(false);
  }

  @Override
  public void run() {
    System.out.println(Thread.currentThread().getName() + " consumer started");
    long objectConsumed = 0;
    while (keepRunning.get()) {
      String obj = takeObjectFromQueue();
      if (obj != null)
        objectConsumed++;
    }
    System.out.println(Thread.currentThread().getName() + " finishing up");
    while (blockingQueue.size() > 0) {
      String obj = takeObjectFromQueue();
      if (obj != null)
        objectConsumed++;
    }
    System.out.println(Thread.currentThread().getName() + " consumer finished: " + objectConsumed);
  }

  private String takeObjectFromQueue() {
    try {
      return blockingQueue.poll(1000, TimeUnit.MILLISECONDS);
    } catch (InterruptedException ex) {
      ex.printStackTrace();
      return null;
    }
  }
}
