package com.learnjava.concurrency.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockExample {
  public static void main(String[] args) {
    Lock lock = new ReentrantLock();
    Runnable runnable = () -> lockSleepUnlock(lock, 1000);

    Thread thread1 = new Thread(runnable, "Thread 1");
    Thread thread2 = new Thread(runnable, "Thread 2");
    Thread thread3 = new Thread(runnable, "Thread 3");

    thread1.start();
    thread2.start();
    thread3.start();
  }

  private static void lockSleepUnlock(Lock lock, long timeMillis) {
    try {
      lock.lock();
      System.out.println(Thread.currentThread().getName() + " holds the lock");
      try {
        Thread.sleep(timeMillis);
      } catch (InterruptedException ex) {
        ex.printStackTrace();
      }
    } finally {
      lock.unlock();
    }
  }
}
