package com.dnt.diag.data;

import java.util.ArrayList;
/**
 * Created with IntelliJ IDEA.
 * User: Rocky Tsui
 * Date: 13-12-2
 * Time: 下午3:38
 * To change this template use File | Settings | File Templates.
 */
public class LiveDataList {
  private static final long serialVersionUID = 1L;
  private long _ptr;
  private ArrayList<LiveDataItem> _elements;
  private LiveDataItem[] _enabledItems;
  private LiveDataItem[] _showedItems;

  private native long nativeGet(int index);

  private native int nativeSize();

  private native void detr();

  private native void collateEnable();

  private native void collateShowed();

  private native int getEnabledCount();

  private native int getShowedCount();

  private native int getEnabledIndex(int index);

  private native int getShowedIndex(int index);

  private void fillShowedItems() {
    int size;
    size = getShowedCount();
    _showedItems = new LiveDataItem[size];
    for (int i = 0; i < size; i++) {
      int index = getShowedIndex(i);
      _showedItems[i] = _elements.get(index);
    }
  }

  private void fillEnabledItems() {
    int size;
    size = getEnabledCount();
    _enabledItems = new LiveDataItem[size];
    for (int i = 0; i < size; i++) {
      int index = getEnabledIndex(i);
      _enabledItems[i] = _elements.get(index);
    }
  }

  public LiveDataList(long pNative) {
    _ptr = pNative;
    int size = nativeSize();

    for (int i = 0; i < size; i++) {
      _elements.add(new LiveDataItem(nativeGet(i)));
    }

    fillEnabledItems();

    fillShowedItems();
  }

  protected void finalize() throws Throwable {
    detr();
    super.finalize();
  }

  public LiveDataItem get(int index) {
    return _elements.get(index);
  }

  public int size() {
    return _elements.size();
  }

  public void collate() {
    collateEnable();
    fillEnabledItems();

    collateShowed();
    fillShowedItems();
  }

  /*
	 * Must call collate() first.
	 */
  public LiveDataItem[] getEnabledItems() {
    return _enabledItems;
  }

  /*
   * Must call colate() first
   */
  public LiveDataItem[] getShowedItems() {
    return _showedItems;
  }

  public native int getNextShowedIndex();

  public native boolean isEmpty();

  public native int getShowedPosition(int index);
}
