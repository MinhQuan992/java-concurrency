package com.learnjava.concurrency.threadlocal;

public class FullThreadLocalExample {
  public static void main(String[] args) {
    MyRunnable runnable = new MyRunnable();

    Thread thread1 = new Thread(runnable);
    Thread thread2 = new Thread(runnable);

    thread1.start();
    thread2.start();
  }

  static class MyRunnable implements Runnable {
    // It is a ThreadLocal object, two threads cannot see each other's values. Thus, they set and get different values.
    private ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
      
    @Override
    public void run() {
      threadLocal.set((int) (Math.random() * 100D));
      try {
        Thread.sleep(2000);
      } catch (Exception e) {
        e.printStackTrace();
      }
      System.out.println(threadLocal.get());
    }
  }
}
