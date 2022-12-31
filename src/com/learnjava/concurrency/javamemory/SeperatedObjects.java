package com.learnjava.concurrency.javamemory;

public class SeperatedObjects {
  public static void main(String[] args) {
    int myLocalVar = 0;
    String myLocalText = "Text";

    MyObject myObject = new MyObject();

    Runnable runnable1 = new MyRunnable(myObject);
    Runnable runnable2 = new MyRunnable(myObject);

    Thread thread1 = new Thread(runnable1, "Thread 1");
    Thread thread2 = new Thread(runnable2, "Thread 2");

    thread1.start();
    thread2.start();
  }
}
