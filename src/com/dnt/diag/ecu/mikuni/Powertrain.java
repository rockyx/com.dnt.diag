package com.dnt.diag.ecu.mikuni;

import com.dnt.diag.db.VehicleDB;
import com.dnt.diag.ecu.AbstractECU;
import com.dnt.diag.io.Commbox;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Rocky Tsui
 * Date: 13-12-2
 * Time: 下午4:46
 * To change this template use File | Settings | File Templates.
 */
public class Powertrain extends AbstractECU {
  public final static int DCJ_16A = 0;
  public final static int DCJ_16C = 1;
  public final static int DCJ_10 = 2;
  public final static int QM200GY_F = 3;
  public final static int QM200_3D = 4;
  public final static int QM200J_2L = 5;

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

  public native String getVersion() throws IOException;

  public native void tpsIdleSetting() throws IOException;

  /*
   * Long Term Learn Value Zone Initialization
   */
  public native void ltlvzInitialization() throws IOException;

  /*
   * ISC Learn Value Initialization
   */
  public native void ilvInitialize() throws IOException;
}
