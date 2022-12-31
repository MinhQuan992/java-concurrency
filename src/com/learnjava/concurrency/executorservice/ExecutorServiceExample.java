package com.learnjava.concurrency.executorservice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceExample {
  public static void main(String[] args) {
    ExecutorService executorService = Executors.newFixedThreadPool(10);
    executorService.execute(getRunnable("Task 1"));
    executorService.execute(getRunnable("Task 2"));
    executorService.execute(getRunnable("Task 3"));
    executorService.shutdown();
  }

  private static Runnable getRunnable(String message) {
    return () -> {
      System.out.println(Thread.currentThread().getName() + ": " + message);
    };
  }
}
