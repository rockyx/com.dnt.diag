package com.dnt.diag.ecu.denso;

import com.dnt.diag.db.VehicleDB;
import com.dnt.diag.io.Commbox;
import com.dnt.diag.ecu.AbstractECU;
/**
 * Created with IntelliJ IDEA.
 * User: Rocky Tsui
 * Date: 13-12-2
 * Time: 下午4:46
 * To change this template use File | Settings | File Templates.
 */
public class Powertrain extends AbstractECU {
  public final static int DCJ_GW250 = 0;
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
}
