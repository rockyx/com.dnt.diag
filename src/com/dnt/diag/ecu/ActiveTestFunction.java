package com.dnt.diag.ecu;

/**
 * Created with IntelliJ IDEA.
 * User: Rocky Tsui
 * Date: 13-12-2
 * Time: 下午4:41
 * To change this template use File | Settings | File Templates.
 */
public class ActiveTestFunction {
  private long _ptr;

  private native void detr();

  public ActiveTestFunction(long pNative) {
    _ptr = pNative;
  }

  public native void changeState(ActiveState state);

  public native void execute(int mode);
}
