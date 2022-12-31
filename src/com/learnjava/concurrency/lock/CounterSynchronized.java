package com.learnjava.concurrency.lock;

public class CounterSynchronized {
  private long count = 0;

  public synchronized void inc() {
    count++;
  }

  public synchronized long getCount() {
    return count;
  }
}
