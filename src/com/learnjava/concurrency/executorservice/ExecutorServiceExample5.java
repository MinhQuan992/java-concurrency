package com.learnjava.concurrency.executorservice;

import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorServiceExample5 {
  public static void main(String[] args) {
    ExecutorService executorService = Executors.newSingleThreadExecutor();

    Future<String> future = executorService.submit(getCallable("Task 1.1"));
    System.out.println(future.isDone());

    boolean wasCancelled = future.cancel(true);
    System.out.println(wasCancelled);

    try {
      String result = future.get();
      System.out.println(result);
    } catch (CancellationException ex) {
      System.out.println("Cannot call Future.get() since task was cancelled");
    } catch (Exception ex) {
      System.out.println("Other errors occurred");
    }

    System.out.println(future.isDone());
    System.out.println(future.isCancelled());

    executorService.shutdown();
  }

  private static Callable<String> getCallable(String message) {
    return () -> Thread.currentThread().getName() + ": " + message;
  }
}
