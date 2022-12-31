package com.learnjava.concurrency.threadlocal;

public class ThreadLocalWithInitialValue {
  public static void main(String[] args) {
    ThreadLocal<Object> threadLocal1 = new ThreadLocal<>() {
      @Override
      protected Object initialValue() {
        return new Object();
      }
    };
    ThreadLocal<Object> threadLocal2 = ThreadLocal.withInitial(() -> new Object());

    Thread thread1 = new Thread(() -> {
      System.out.println("Thread Local 1: " + threadLocal1.get());
      System.out.println("Thread Local 2: " + threadLocal2.get());
    });
    Thread thread2 = new Thread(() -> {
      System.out.println("Thread Local 1: " + threadLocal1.get());
      System.out.println("Thread Local 2: " + threadLocal2.get());
    });

    thread1.start();
    thread2.start();
  }
}
