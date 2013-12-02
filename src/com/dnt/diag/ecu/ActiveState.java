package com.dnt.diag.ecu;

/**
 * Created with IntelliJ IDEA.
 * User: Rocky Tsui
 * Date: 13-12-2
 * Time: 下午4:42
 * To change this template use File | Settings | File Templates.
 */
public enum ActiveState {
  Positive(1), Idle(0), Negative(-1), Stop(-2);
  private int _value;

  ActiveState(int value) {
    _value = value;
  }

  public int getValue() {
    return _value;
  }
}
