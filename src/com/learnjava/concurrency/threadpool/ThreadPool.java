package com.learnjava.concurrency.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ThreadPool {
  private BlockingQueue<Runnable> taskQueue = null;
  private List<PoolThreadRunnable> runnables = new ArrayList<>();
  private boolean isStopped = false;

  public ThreadPool(int numberOfThreads, int maxNumberOfTasks) {
    taskQueue = new ArrayBlockingQueue<>(maxNumberOfTasks);
    for (int i = 0; i < numberOfThreads; i++) {
      PoolThreadRunnable poolThreadRunnable = new PoolThreadRunnable(taskQueue);
      runnables.add(poolThreadRunnable);
    }
    for (Runnable runnable: runnables) {
      new Thread(runnable).start();
    }
  }

  public synchronized void execute(Runnable task) {
    if (isStopped) {
      throw new IllegalStateException("Thread Pool is stopped");
    }
    taskQueue.offer(task);
  }

  public synchronized void stop() {
    isStopped = true;
    for (PoolThreadRunnable runnable : runnables) {
      runnable.doStop();
    }
  }

  public synchronized void waitUntilAllTasksFinished() {
    while (taskQueue.size() > 0) {
      try {
        Thread.sleep(1);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
