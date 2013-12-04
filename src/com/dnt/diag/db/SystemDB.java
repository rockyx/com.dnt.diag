package com.dnt.diag.db;

/**
 * Created with IntelliJ IDEA.
 * User: Rocky Tsui
 * Date: 13-12-2
 * Time: 下午3:42
 * To change this template use File | Settings | File Templates.
 */
public class SystemDB {
  static {
    System.loadLibrary("dntdiag");
  }
  private SystemDB() {

  }

  public native static void init(String path);

  public native static String queryText(String name);
}
