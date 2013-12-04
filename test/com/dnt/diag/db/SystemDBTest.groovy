package com.dnt.diag.db

import com.dnt.diag.Register
import org.junit.Before
import org.junit.Test

import static junit.framework.Assert.assertEquals

/**
 * Created with IntelliJ IDEA.
 * User: Rocky Tsui
 * Date: 13-12-4
 * Time: 上午11:51
 * To change this template use File | Settings | File Templates.
 */
class SystemDBTest {
  private String zhCNReg
  private String enUSReg
  private String dbPath
  private String zhCNPath
  private String enUSPath

  SystemDBTest() {
    zhCNReg = '''whVht1so+a3p8MBQlnRUnJQF+GEVrAVajXEnFJFX5FqbCnHCVPWToT2sRtxHqwvT2qNOLwLk
OifpSMGhKH8Dh2rfK5f9DMtttQ+eFP3tDd+bQcEx+UOsuuomppZJQILFUzFGnskVy0xqB64+
x0jK54XTnqRCMTtdLfePzR26O1I3sZ7H4Vf9Jy19ijT0ovepadCqcR9OngnzzxZXC2loOh6c
1HEpM7jLMjTQPRObt9BFXYtweHcP6YxSDeg7RiZhYmTKQb0tk5PRq+E/6WGmalwDzzTp3TsQ
S66Ch34Tb/g3NBx/9Des+eeRt+OT8i2gmhaJKvdKEylJGXFy+RCy+g=='''
    enUSReg = '''FL/u8PI5ocI2n5jZ7AzsSu7eLDzVu3b2FU8VjtBVbRrzmuil9ZBXMj/nRCDDbICN9eOpK3NT
2h/LXOBYjQIvTsBxoD4+/Vkox9iwbvOvE5h2rpWoV+CXTWg7RFgox+KnZtVDToVRqV9Vaknx
Jb1uyc/VlF+AuElJAw7g4m/2Wse9YbtYzAQ1v7GiX/vuJHrspqNMiTO2tDUXZZ7tdk09asFZ
XuXoVRdjYf1MqKXzL65az0vecmt/hplcg4SPjzM1FUxTQmVXVDnTSUihD2+m4c6kJxQ0TjH+
6m3o3Mv5cG+mjzIS9TnClsoZaYIBjvNZKg7zrfQ7pBCB0d9dQ+MRRA=='''
    dbPath = new File("test").absolutePath
    zhCNPath = new File("test/zh-CN").absolutePath
    enUSPath = new File("test/en-US").absolutePath
  }

  @Before
  void setUp() {
    File dir = new File("test/zh-CN")
    if (dir.exists()) {
      dir.deleteDir()
    }
    dir.mkdir()

    dir = new File("test/en-US");
    if (dir.exists()) {
      dir.deleteDir()
    }
    dir.mkdir()

    File demo = new File("test/zh-CN/demo.dat")
    demo.createNewFile()
    demo.write(zhCNReg)
    Register.init(zhCNPath)

    demo = new File("test/en-US/demo.dat")
    demo.createNewFile()
    demo.write(enUSReg)
    Register.init(enUSPath)

    SystemDB.init(dbPath)
  }

  @Test
  void testQueryText() {
    String queryName = "Communicating"
    String zhCNExpect = "正在通讯..."
    String enUSExpect = "Communicating..."

    Register.init(zhCNPath)
    String result = SystemDB.queryText(queryName)
    assertEquals(zhCNExpect, result)

    Register.init(enUSPath)
    result = SystemDB.queryText(queryName)
    assertEquals(enUSExpect, result)
  }
}
