package com.dnt.diag.ecu.bosch.canbus;

import com.dnt.diag.db.VehicleDB;
import com.dnt.diag.ecu.AbstractECU;
import com.dnt.diag.io.Commbox;

/**
 * Created with IntelliJ IDEA.
 * User: Rocky Tsui
 * Date: 13-12-2
 * Time: 下午4:44
 * To change this template use File | Settings | File Templates.
 */
public class Chassis extends AbstractECU {
  public static final int BESTURN_B50_ABS = 0;
  private long _ptr;

  private native void ctor(Commbox box, VehicleDB db, int model);

  private native void detr();

  private native long cast();

  public Chassis(Commbox box, VehicleDB db, int model) {
    ctor(box, db, model);
    super.setNative(cast());
  }

  protected void finalize() throws Throwable {
    detr();
    super.finalize();
  }
}
