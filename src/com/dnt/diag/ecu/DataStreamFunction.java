package com.dnt.diag.ecu;

import com.dnt.diag.data.LiveDataList;

/**
 * Created with IntelliJ IDEA.
 * User: Rocky Tsui
 * Date: 13-12-2
 * Time: 下午4:41
 * To change this template use File | Settings | File Templates.
 */
public class DataStreamFunction {
  private long _ptr;
  private LiveDataList _lds;

  private native void detr();

  private native long nativeGetLiveData();

  public DataStreamFunction(long pNative) {
    _ptr = pNative;
    _lds = null;
  }

  protected void finalize() throws Throwable {
    detr();
    super.finalize();
  }

  public LiveDataList getLiveData() {
    if (_lds == null) {
      _lds = new LiveDataList(nativeGetLiveData());
    }

    return _lds;
  }

  public native void stopRead();

  public native void stopCalc();

  public native void readData();

  public native void readDataOnce();

  public native void calcData();

  public native void calcDataOnce();
}
