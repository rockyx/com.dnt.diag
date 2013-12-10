package com.dnt.diag.ecu.mikuni

import com.dnt.diag.Register
import com.dnt.diag.data.LiveDataItem
import com.dnt.diag.data.LiveDataList
import com.dnt.diag.db.VehicleDB
import com.dnt.diag.ecu.DataStreamFunction
import com.dnt.diag.ecu.TroubleCodeFunction
import com.dnt.diag.io.Commbox
import org.junit.After
import org.junit.Before
import org.junit.Test

/**
 * Created by Rocky Tsui on 13-12-9.
 */
class PowertrainTest extends GroovyTestCase {
  private String zhCNPath
  private String enUSPath
  private Commbox box
  private VehicleDB db
  private Powertrain ecu

  @Before
  void setUp() {
    super.setUp()

    zhCNPath = new File("test/zh-CN").absolutePath
    enUSPath = new File("test/en-US").absolutePath

    Register.init(zhCNPath)

    box = new Commbox(Commbox.C168)
    box.connect()

    db = new VehicleDB("test", "DCJ")
    db.open()

    ecu = new Powertrain(box, db, Powertrain.DCJ_16A)
    ecu.ioChannelInit()
  }

  @After
  void tearDown() {
    db.close()
    box.disconnect()
  }

  @Test
  void testGetECUVersion() {
    try {
      println(ecu.version)
    } catch (IOException ex) {
      assertTrue(ex.message, false)
    }
  }

  @Test
  void testTpsIdleSetting() {
    try {
      ecu.tpsIdleSetting()
    } catch (IOException ex) {
      assertTrue(ex.message, false)
    }
  }

  @Test
  void testLtlvzInitialization() {
    try {
      ecu.ltlvzInitialization()
    } catch (IOException ex) {
      assertTrue(ex.message, false)
    }
  }

  @Test
  void testIlvInitialize() {
    try {
      ecu.ilvInitialize()
    } catch (IOException ex) {
      assertTrue(ex.message, false)
    }
  }

  @Test
  void testTroubleCode() {
    try {
      TroubleCodeFunction tcf = ecu.troubleCode
      tcf.current()
      tcf.history()
      tcf.clear()
    } catch (IOException ex) {
      assertTrue(ex.message, false)
    }
  }

  @Test
  void testDataStream() {
    try {
      DataStreamFunction dsf = ecu.dataStream

      LiveDataList lds = dsf.liveData

      for (LiveDataItem item : lds) {
        item.isShowed = true
      }

      lds.collate()

      dsf.start()
      Thread.sleep(10000)
      dsf.stop()

      dsf.once()

    } catch (IOException ex) {
      ex.printStackTrace()
      assertTrue(ex.message, false)
    }

  }
}
