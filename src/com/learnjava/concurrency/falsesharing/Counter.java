package com.learnjava.concurrency.falsesharing;

public class Counter {
  public volatile long count1 = 0;
  public volatile long count2 = 0;
}
