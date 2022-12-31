package com.learnjava.concurrency.threadpool;

import java.util.concurrent.BlockingQueue;

public class PoolThreadRunnable implements Runnable {
  private Thread thread = null;
  private BlockingQueue<Runnable> taskQueue = null;
  private boolean isStopped = false;

  public PoolThreadRunnable(BlockingQueue<Runnable> blockingQueue) {
    taskQueue = blockingQueue;
  }

  @Override
  public void run() {
    thread = Thread.currentThread();
    while (!isStopped()) {
      try {
        Runnable runnable = (Runnable) taskQueue.take();
        runnable.run();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  public synchronized void doStop() {
    isStopped = true;
    thread.interrupt();
  }

  public synchronized boolean isStopped() {
    return isStopped;
  }
}
