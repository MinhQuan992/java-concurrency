package com.learnjava.concurrency.synchronization;

public class SharedMonitorObject {
  private final Object monitor;

  private int counter = 0;

  public SharedMonitorObject(Object monitor) {
    if (monitor == null) {
      throw new IllegalArgumentException("Monitor object cannot be null");
    }
    this.monitor = monitor;
  }

  public void increaseCounter() {
    synchronized (this.monitor) {
      this.counter++;
    }
  }
}
