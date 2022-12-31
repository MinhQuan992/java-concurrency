package com.learnjava.concurrency.compareandswap;

public class CompareAndSwapExample {
  public static void main(String[] args) {
    OptimisticLockCounter counter = new OptimisticLockCounter();

    Thread thread1 = new Thread(getRunnable(counter));
    Thread thread2 = new Thread(getRunnable(counter));

    thread1.start();
    thread2.start();
  }

  private static Runnable getRunnable(OptimisticLockCounter counter) {
    return () -> {
      for (int i = 0; i < 1_000_000; i++) {
        counter.inc();
      }
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println(Thread.currentThread().getName() + ": " + counter.getCount());
    };
  }
}
