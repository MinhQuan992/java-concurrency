package com.learnjava.concurrency.javamemory;

public class SharedObjects {
  public static void main(String[] args) {
    int myLocalVar = 0;
    String myLocalText = "Text";

    MyObject myObject = new MyObject();

    Runnable runnable = new MyRunnable(myObject);

    Thread thread1 = new Thread(runnable, "Thread 1");
    Thread thread2 = new Thread(runnable, "Thread 2");

    thread1.start();
    thread2.start();
  }
}
