package com.dnt.diag.data;

/**
 * Created with IntelliJ IDEA.
 * User: Rocky Tsui
 * Date: 13-12-2
 * Time: 下午3:36
 * To change this template use File | Settings | File Templates.
 */
public class LiveDataItem {
  private long _ptr;
  private String _shortName;
  private String _content;
  private String _unit;
  private String _defaultValue;
  private String _description;
  private String _cmdName;
  private String _cmdClass;
  private String _minValue;
  private String _maxValue;
  private byte[] _command;

  private native void detr();

  protected LiveDataItem(long pNative) {
    _ptr = pNative;
    _shortName = null;
    _content = null;
    _unit = null;
    _defaultValue = null;
    _description = null;
    _cmdName = null;
    _cmdClass = null;
    _command = null;
  }

  protected void finalize() throws Throwable {
    detr();
  }

  public native String getShortName();

  public native String getContent();

  public native String getUnit();

  public native String getDefaultValue();

  public native String getDescription();

  public native String getCmdClass();

  public native String getCmdName();

  public native String getMaxValue();

  public native String getMinValue();

  public native int getIndex();

  public native int getPosition();

  public native boolean isEnabled();

  public native boolean isShowed();

  public native boolean isOutOfRange();

  public native byte[] getCommand();

  public native String getValue();
}
