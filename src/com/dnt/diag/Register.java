package com.dnt.diag;

/**
 * Created with IntelliJ IDEA.
 * User: Rocky Tsui
 * Date: 13-12-2
 * Time: 下午3:25
 * To change this template use File | Settings | File Templates.
 */
public class Register {
  static {
    System.loadLibrary("dntdiag");
  }

  public native static void init(String path);

  public native static String getIdCode();

  public native static void save(String reg);

  public native static boolean getIsReged();
}
