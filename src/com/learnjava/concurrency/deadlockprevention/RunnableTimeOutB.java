package com.learnjava.concurrency.deadlockprevention;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

public class RunnableTimeOutB implements Runnable {
  private final Lock lock1;
  private final Lock lock2;

  public RunnableTimeOutB(Lock lock1, Lock lock2) {
    this.lock1 = lock1;
    this.lock2 = lock2;
  }

  @Override
  public void run() {
    String threadName = Thread.currentThread().getName();
    while (true) {
      int failureCount = 0;

      while (!tryLockBothLocks()) {
        failureCount++;
        String errorMessage = String.format("%s failed to lock both Locks. Waiting a bit before retrying [%d tries]", threadName, failureCount);
        System.err.println(errorMessage);
        sleep(100L * ((long) Math.random()));
      }

      if (failureCount > 0) {
        String successMessage = String.format("%s succeeded in locking both locks - after %d failures", threadName, failureCount);
        System.out.println(successMessage);
      }

      lock2.unlock();
      lock1.unlock();
    }
  }

  private boolean tryLockBothLocks() {
    String threadName = Thread.currentThread().getName();

    try {
      boolean lock2Succeeded = lock2.tryLock(1000, TimeUnit.MILLISECONDS);
      if (!lock2Succeeded) {
        return false;
      }
    } catch (InterruptedException ex) {
      System.out.println(threadName + " interrupted trying to lock Lock 2");
      return false;
    }

    try {
      boolean lock1Succeeded = lock1.tryLock(1000, TimeUnit.MILLISECONDS);
      if (!lock1Succeeded) {
        lock2.unlock();
        return false;
      }
    } catch (InterruptedException ex) {
      System.out.println(threadName + " interrupted trying to lock Lock 1");
      lock2.unlock();
      return false;
    }

    return true;
  }

  private void sleep(long timeMillis) {
    try {
      Thread.sleep(timeMillis);
    } catch (InterruptedException ex) {
      ex.printStackTrace();
    }
  }
}
