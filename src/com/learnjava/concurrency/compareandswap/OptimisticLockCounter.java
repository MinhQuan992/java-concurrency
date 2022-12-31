package com.learnjava.concurrency.compareandswap;

import java.util.concurrent.atomic.AtomicLong;

public class OptimisticLockCounter {
  private AtomicLong count = new AtomicLong();

  public void inc() {
    boolean incSuccessful = false;

    while (!incSuccessful) {
      long value = count.get();
      long newValue = value + 1;
      incSuccessful = count.compareAndSet(value, newValue);
    }
  }

  public long getCount() {
    return count.get();
  }
}
