package com.dnt.diag.db

import org.junit.Before
import org.junit.Test

/**
 * Created by Rocky Tsui on 13-12-4.
 */
class VehicleDBTest extends GroovyTestCase {
  VehicleDB db

  @Before
  void setUp() {
    db = null
  }

  @Test
  void test() {
    try {
      db = new VehicleDB("test", "DCJ")
      db.open()
      db.close()
    } catch (IOException ex) {
      assertFalse("Vehicle DB Open Fail!", false)
    }

    try {
      db = new VehicleDB("", "")
      db.open()
      db.close()
      assertFalse("Vehicle DB Open Success but should Fail!", false)
    } catch (IOException ex) {
      // This is what we want to.
    }
  }
}
