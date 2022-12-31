package com.learnjava.concurrency.synchronization;

import java.util.function.Consumer;

public class SynchronizedLambda {
  private static Object object;

  public static synchronized void setObject(Object o) {
    object = o;
  }

  public static void consumeObject(Consumer consumer) {
    consumer.accept(object);
  }

  public static void main(String[] args) {
    consumeObject((obj) -> {
      synchronized (SynchronizedLambda.class) {
        System.out.println(obj);
      }
    });
  }
}
