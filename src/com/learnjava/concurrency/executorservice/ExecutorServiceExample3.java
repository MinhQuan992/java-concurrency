package com.learnjava.concurrency.executorservice;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceExample3 {
  public static void main(String[] args) {
    ExecutorService executorService = Executors.newFixedThreadPool(3);

    List<Callable<String>> callables = new ArrayList<>();
    callables.add(getCallable("Task 1.1"));
    callables.add(getCallable("Task 1.2"));
    callables.add(getCallable("Task 1.3"));

    try {
      String result = executorService.invokeAny(callables);
      System.out.println(result);
    } catch (Exception e) {
      // TODO: handle exception
    }

    executorService.shutdown();
  }

  private static Callable<String> getCallable(String message) {
    return () -> Thread.currentThread().getName() + ": " + message;
  }
}
