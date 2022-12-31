package com.learnjava.concurrency.racecondition;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RaceConditionsExample4 {
  public static void main(String[] args) {
    // Check - then - act
    Map<String, String> shareMap = new ConcurrentHashMap<>();

    Thread thread1 = new Thread(getRunnable(shareMap));
    Thread thread2 = new Thread(getRunnable(shareMap));

    thread1.start();
    thread2.start();
  }

  private static Runnable getRunnable(Map<String, String> sharedMap) {
    return () -> {
      for (int i = 0; i < 1_000_000; i++) {
        if (sharedMap.containsKey("key")) {
          String value = sharedMap.remove("key");
          if (value == null) {
            System.out.println("Iteration " + i + ": Value for key was null");
          }
        } else {
          sharedMap.put("key", "value");
        }
      }
    };
  }
}
