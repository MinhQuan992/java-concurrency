package com.learnjava.concurrency.blockingqueue;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {
  private final BlockingQueue<String> blockingQueue;

  public Consumer(BlockingQueue<String> blockingQueue) {
    this.blockingQueue = blockingQueue;
  }

  @Override
  public void run() {
    while (true) {
      try {
        String element = blockingQueue.take();
        String text = Thread.currentThread().getName() + " consumed " + element;
        System.out.println(text);
      } catch (InterruptedException ex) {
        ex.printStackTrace();
      }
    }
  }
}
