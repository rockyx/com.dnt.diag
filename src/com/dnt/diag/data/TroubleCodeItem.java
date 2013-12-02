package com.dnt.diag.data;

/**
 * Created with IntelliJ IDEA.
 * User: Rocky Tsui
 * Date: 13-12-2
 * Time: 下午3:28
 * To change this template use File | Settings | File Templates.
 */
public class TroubleCodeItem {
  private long _ptr;
  private String _code;
  private String _content;
  private String _description;

  private native void detr();

  protected TroubleCodeItem(long pNative) {
    _ptr = pNative;
    _code = null;
    _content = null;
  }

  protected void finalize() throws Throwable {
    detr();
    super.finalize();
  }

  public native String getCode();

  public native String getContent();

  public native String getDescription();
}
