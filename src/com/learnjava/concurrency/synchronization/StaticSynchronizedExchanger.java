package com.learnjava.concurrency.synchronization;

public class StaticSynchronizedExchanger {
  private static Object object = null;

  public static synchronized void setObject(Object obj) {
    object = obj;
  }

  public static synchronized Object getObject() {
    return object;
  }

  public static void setObj(Object obj) {
    synchronized (StaticSynchronizedExchanger.class) {
      object = obj;
    }
  }

  public static Object getObj() {
    synchronized (StaticSynchronizedExchanger.class) {
      return object;
    }
  }
}
