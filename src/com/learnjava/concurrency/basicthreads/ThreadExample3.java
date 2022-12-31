package com.learnjava.concurrency.basicthreads;

public class ThreadExample3 {
  public static void main(String[] args) {
    StoppableRunnable stoppableRunnable = new StoppableRunnable();
    Thread thread = new Thread(stoppableRunnable, "My Thread");
    thread.start();

    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    System.out.println("Requesting Stop");
    stoppableRunnable.requestStop();
    System.out.println("Stop Requested");
  }

  public static class StoppableRunnable implements Runnable {
    private boolean stopRequested = false;

    public synchronized void requestStop() {
      this.stopRequested = true;
    }

    public synchronized boolean isStopRequested() {
      return this.stopRequested;
    }

    private void sleep(long milliseconds) {
      try {
        Thread.sleep(milliseconds);
      } catch(InterruptedException exception) {
        exception.printStackTrace();
      }
    }

    @Override
    public void run() {
      System.out.println("StoppableRunnable Running");
      while (!isStopRequested()) {
        sleep(1000);
        System.out.println("...");
      }
      System.out.println("StoppableRunnable Stopped");
    }
  }
}
