package com.dnt.diag.ecu;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Rocky Tsui
 * Date: 13-12-2
 * Time: 下午4:42
 * To change this template use File | Settings | File Templates.
 */
public class AbstractECU {
  private long _ptr;
  private ActiveTestFunction _activeTestFunction;
  private DataStreamFunction _dataStreamFunction;
  private DataStreamFunction _freezeStreamFunction;
  private TroubleCodeFunction _troubleCodeFunction;

  private native void detr();

  private native void nativeIOChannelInit() throws IOException;

  private native long nativeGetTroubleCode();

  private native long nativeGetDataStream();

  private native long nativeGetFreezeStream();

  private native long nativeGetActiveTest();

  protected AbstractECU() {
    _ptr = 0;
    _activeTestFunction = new ActiveTestFunction(0);
    _dataStreamFunction = new DataStreamFunction(0);
    _freezeStreamFunction = new DataStreamFunction(0);
    _troubleCodeFunction = new TroubleCodeFunction(0);
  }

  protected void setNative(long pNative) {
    _ptr = pNative;
  }

  protected void finalize() throws Throwable {
    detr();
    super.finalize();
  }

  public void ioChannelInit() throws IOException {
    nativeIOChannelInit();
    _activeTestFunction = new ActiveTestFunction(nativeGetActiveTest());
    _dataStreamFunction = new DataStreamFunction(nativeGetDataStream());
    _freezeStreamFunction = new DataStreamFunction(nativeGetFreezeStream());
    _troubleCodeFunction = new TroubleCodeFunction(nativeGetTroubleCode());
  }

  public ActiveTestFunction getActiveTest() {
    return _activeTestFunction;
  }

  public DataStreamFunction getDataStream() {
    return _dataStreamFunction;
  }

  public DataStreamFunction getFreezeStream() {
    return _freezeStreamFunction;
  }

  public TroubleCodeFunction getTroubleCode() {
    return _troubleCodeFunction;
  }
}
