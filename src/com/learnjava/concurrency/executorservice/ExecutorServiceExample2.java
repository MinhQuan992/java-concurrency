package com.learnjava.concurrency.executorservice;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorServiceExample2 {
  public static void main(String[] args) {
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    
    Future<String> future = executorService.submit(getCallable("Task 1"));

    System.out.println(future.isDone());

    try {
      String result = future.get();
      System.out.println(result);
    } catch (Exception e) {
      // TODO: handle exception
    }

    System.out.println(future.isDone());

    executorService.shutdown();
  }

  private static Callable<String> getCallable(String message) {
    return () -> Thread.currentThread().getName() + ": " + message;
  }
}
