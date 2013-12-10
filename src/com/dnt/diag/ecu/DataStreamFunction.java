package com.dnt.diag.ecu;

import com.dnt.diag.data.LiveDataList;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: Rocky Tsui
 * Date: 13-12-2
 * Time: 下午4:41
 * To change this template use File | Settings | File Templates.
 */
public class DataStreamFunction {
  private class DataStreamRead implements Runnable {
    private DataStreamFunction _ds;

    public DataStreamRead(DataStreamFunction ds) {
      this._ds = ds;
    }

    @Override
    public void run() {
      _ds.readData();
    }
  }

  private class DataStreamCalc implements Runnable {
    private DataStreamFunction _ds;

    public DataStreamCalc(DataStreamFunction ds) {
      this._ds = ds;
    }

    @Override
    public void run() {
      _ds.calcData();
    }
  }

  private class DataStreamOnce implements Runnable {
    private DataStreamFunction _ds;

    public DataStreamOnce(DataStreamFunction ds) {
      this._ds = ds;
    }

    @Override
    public void run() {
      _ds.readDataOnce();
      _ds.calcDataOnce();
    }
  }

  private long _ptr;
  private ExecutorService _executor;
  private DataStreamRead _read;
  private DataStreamCalc _calc;
  private DataStreamOnce _once;
  private LiveDataList _lds;

  private native void detr();

  private native long nativeGetLiveData();

  private native void stopRead();

  private native void stopCalc();

  private native void readData();

  private native void readDataOnce();

  private native void calcData();

  private native void calcDataOnce();

  public DataStreamFunction(long pNative) {
    _ptr = pNative;
    _lds = null;
  }

  protected void finalize() throws Throwable {
    stop();
    detr();
    super.finalize();
  }

  public LiveDataList getLiveData() {
    if (_lds == null) {
      _lds = new LiveDataList(nativeGetLiveData());
    }

    return _lds;
  }

  public void start() {
    _executor = Executors.newCachedThreadPool();
    _read = new DataStreamRead(this);
    _calc = new DataStreamCalc(this);
    _executor.execute(_read);
    _executor.execute(_calc);
  }

  public void stop() {
    stopCalc();
    stopRead();
    _executor.shutdown();
    try {
      _executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
    } catch (InterruptedException e) {
    }
  }

  public void once() {
    _executor = Executors.newCachedThreadPool();
    _once = new DataStreamOnce(this);
    _executor.execute(_once);
    _executor.shutdown();
    try {
      _executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
