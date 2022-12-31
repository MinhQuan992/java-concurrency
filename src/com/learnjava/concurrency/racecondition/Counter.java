package com.learnjava.concurrency.racecondition;

public class Counter {
  private long count = 0;

  public synchronized long incAndGet() {
    count++;
    return count;
  }

  public long get() {
    return count;
  }
}
