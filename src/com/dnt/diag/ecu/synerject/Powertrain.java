package com.dnt.diag.ecu.synerject;

import java.io.IOException;

import com.dnt.diag.db.VehicleDB;
import com.dnt.diag.ecu.AbstractECU;
import com.dnt.diag.io.Commbox;

/**
 * Created with IntelliJ IDEA.
 * User: Rocky Tsui
 * Date: 13-12-2
 * Time: 下午4:47
 * To change this template use File | Settings | File Templates.
 */
public class Powertrain extends AbstractECU {
  public static final int QM125T_8H = 0;
  public static final int QM250GY = 1;
  public static final int QM250T = 2;

  private long _ptr;

  private native void ctor(Commbox box, VehicleDB db, int model);

  private native void detr();

  private native long cast();

  public Powertrain(Commbox box, VehicleDB db, int model) {
    ctor(box, db, model);
    super.setNative(cast());
  }

  protected void finalize() throws Throwable {
    detr();
    super.finalize();
  }

  public native String getECUVersion() throws IOException;
}
