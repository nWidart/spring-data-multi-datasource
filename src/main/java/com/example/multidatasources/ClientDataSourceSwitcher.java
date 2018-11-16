package com.example.multidatasources;

public class ClientDataSourceSwitcher {

  private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

  public static String getClientName() {
    if (contextHolder.get() == null) {
      return "source1";
    }
    return contextHolder.get();
  }

  public static void setClientName(String clientName) {
    if (clientName == null) {
      throw new NullPointerException();
    }
    contextHolder.set(clientName);
  }

  public static void clearClientName() {
    contextHolder.remove();
  }
}
