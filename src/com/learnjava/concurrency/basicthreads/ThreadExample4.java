package com.learnjava.concurrency.basicthreads;

public class ThreadExample4 {
  public static void main(String[] args) {
    Runnable runnable = () -> {
      while (true) {
        sleep(1000);
        System.out.println("Running");
      }
    };

    Thread thread = new Thread(runnable);

    // Try to comment the following line of code to see the differences.
    thread.setDaemon(true);

    thread.start();
    sleep(3100);
    System.out.println("After sleeping 3100ms...");
  }

  public static void sleep(long millis) {
    try {
      Thread.sleep(millis);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
