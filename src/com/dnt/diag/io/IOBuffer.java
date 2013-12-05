package com.dnt.diag.io;

/**
 * Created with IntelliJ IDEA.
 * User: Rocky Tsui
 * Date: 13-12-2
 * Time: 下午3:43
 * To change this template use File | Settings | File Templates.
 */
public class IOBuffer {
  static {
    System.loadLibrary("dntdiag");
  }
  private long _ptr;

  private native void ctor();

  private native void detr() throws NullPointerException;

  public IOBuffer() {
    ctor();
  }

  protected void finalize() throws Throwable {
    detr();
    super.finalize();
  }

  public native void pushToFromEcuBuffer(byte[] buff, int offset, int count)
          throws NullPointerException, IndexOutOfBoundsException;

  public native int popFromToEcuBuffer(byte[] buff, int offset, int count)
          throws NullPointerException, IndexOutOfBoundsException;

  public native int toEcuBufferBytesAvailable() throws NullPointerException;

  public long getNative() {
    return _ptr;
  }
}
