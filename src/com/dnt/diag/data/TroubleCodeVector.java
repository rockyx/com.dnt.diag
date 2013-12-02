package com.dnt.diag.data;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Rocky Tsui
 * Date: 13-12-2
 * Time: 下午3:33
 * To change this template use File | Settings | File Templates.
 */
public class TroubleCodeVector {
  private long _ptr;
  private ArrayList<TroubleCodeItem> _elements;

  private native void detr();

  private native int nativeSize();

  private native long nativeGet(int index);

  protected TroubleCodeVector(long pNative) {
    _ptr = pNative;
    int size = nativeSize();
    for (int i = 0; i < size; i++) {
      _elements.add(new TroubleCodeItem(nativeGet(i)));
    }
  }

  protected void finalize() throws Throwable {
    detr();
    super.finalize();
  }

  public TroubleCodeItem get(int index) {
    return _elements.get(index);
  }
}
