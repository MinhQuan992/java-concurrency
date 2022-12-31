package com.learnjava.concurrency.basicthreads;

public class ThreadExample {
  public static void main(String[] args) {
    MyThread myThread = new MyThread();
    myThread.start();

    Thread myThread2 = new Thread(new MyRunnable());
    myThread2.start();
  }

  public static class MyThread extends Thread {
    @Override
    public void run() {
      System.out.println("MyThread running");
      System.out.println("MyThread finished");
    }
  }

  public static class MyRunnable implements Runnable {
    @Override
    public void run() {
      System.out.println("MyRunnable running");
      System.out.println("MyRunnable finished");
    }
  }
}