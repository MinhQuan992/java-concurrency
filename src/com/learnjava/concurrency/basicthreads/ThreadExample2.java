package com.learnjava.concurrency.basicthreads;

public class ThreadExample2 {
  public static void main(String[] args) {
    Runnable runnable = () -> {
      String threadName = Thread.currentThread().getName();
      System.out.println(threadName + " running");

      try {
        Thread.sleep(2000);
      } catch(InterruptedException exception) {
        exception.printStackTrace();
      }

      System.out.println(threadName + " finished");
    };
    
    Thread thread = new Thread(runnable, "My first thread");
    thread.start();

    Thread thread2 = new Thread(runnable, "My second thread");
    thread2.start();
  }
}
