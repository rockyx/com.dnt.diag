package com.dnt.diag.db;

import java.io.IOException;
/**
 * Created with IntelliJ IDEA.
 * User: Rocky Tsui
 * Date: 13-12-2
 * Time: 下午3:41
 * To change this template use File | Settings | File Templates.
 */
public class VehicleDB {
  private long _ptr;

  private native void ctor(String path, String name);

  private native void detr();

  public VehicleDB(String path, String name) {
    ctor(path, name);
  }

  protected void finalize() throws Throwable {
    detr();
    super.finalize();
  }

  public native void open() throws IOException;

  public native void close();
}
