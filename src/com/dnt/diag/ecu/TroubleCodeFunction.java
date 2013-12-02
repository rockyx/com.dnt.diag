package com.dnt.diag.ecu;

import java.io.IOException;

import com.dnt.diag.data.TroubleCodeVector;
/**
 * Created with IntelliJ IDEA.
 * User: Rocky Tsui
 * Date: 13-12-2
 * Time: 下午4:39
 * To change this template use File | Settings | File Templates.
 */
public class TroubleCodeFunction {
  private long _ptr;

  private native void detr();

  protected TroubleCodeFunction(long pNative) {
    _ptr = pNative;
  }

  protected void finalize() throws Throwable {
    detr();
    super.finalize();
  }

  public native void current() throws IOException;

  public native void history() throws IOException;

  public native void clear() throws IOException;

  public native TroubleCodeVector getTroubleCodes();
}
