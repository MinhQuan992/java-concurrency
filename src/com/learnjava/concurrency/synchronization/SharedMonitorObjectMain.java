package com.learnjava.concurrency.synchronization;

public class SharedMonitorObjectMain {
  public static void main(String[] args) {
    Object monitor1 = new Object();
    SharedMonitorObject sharedMonitorObject1 = new SharedMonitorObject(monitor1);
    SharedMonitorObject sharedMonitorObject2 = new SharedMonitorObject(monitor1);

    sharedMonitorObject1.increaseCounter();
    sharedMonitorObject2.increaseCounter();

    Object monitor2 = new Object();
    SharedMonitorObject sharedMonitorObject3 = new SharedMonitorObject(monitor2);

    sharedMonitorObject3.increaseCounter();
  }
}
