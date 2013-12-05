package com.dnt.diag.io

import static junit.framework.Assert.assertTrue

import org.junit.Before
import org.junit.Test

/**
 * Created by Rocky Tsui on 13-12-4.
 */
class CommboxTest {
  private Commbox commbox

  @Before
  void setUp() {
  }

  @Test
  void c168CommboxTest() {
    try {
      commbox = new Commbox(Commbox.C168)
      commbox.connect()
      Thread.sleep(5000)
      commbox.disconnect()
    } catch (IOException ex) {
      ex.printStackTrace()
      assertTrue("C168 Commbox Test Fail!", false)
    }
  }
}
