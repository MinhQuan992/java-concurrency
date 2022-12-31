package com.learnjava.concurrency.blockingqueue;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {
  private final BlockingQueue<String> blockingQueue;

  public Producer(BlockingQueue<String> blockingQueue) {
    this.blockingQueue = blockingQueue;
  }

  @Override
  public void run() {
    while (true) {
      long timeMillis = System.currentTimeMillis();
      try {
        blockingQueue.put("" + timeMillis);
      } catch (InterruptedException ex) {
        System.out.println("Producer was interrupted");
      }
      sleep(1000);
    }
  }

  private void sleep(long timeMillis) {
    try {
      Thread.sleep(timeMillis);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}
