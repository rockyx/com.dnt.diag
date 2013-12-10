package com.dnt.diag.io;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeoutException;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: Rocky Tsui
 * Date: 13-12-2
 * Time: 下午3:44
 * To change this template use File | Settings | File Templates.
 */
public class SerialPort {
  static {
    System.loadLibrary("dntdiag");
  }

  public enum Parity {
    None(0), Odd(1), Even(2), Mark(3), Space(4);

    private int _value;

    private Parity(int value) {
      _value = value;
    }

    public int getValue() {
      return _value;
    }
  }

  public enum StopBits {
    One(0), Two(1), One5(2);
    private int _value;

    private StopBits(int value) {
      _value = value;
    }

    public int getValue() {
      return _value;
    }
  }

  public enum Handshake {
    None(0), XOnXOff(1), RequestToSend(2), RequestToSendXOnXOff(3);
    private int _value;

    private Handshake(int value) {
      _value = value;
    }

    public int getValue() {
      return _value;
    }
  }

  private long _ptr;

  private native void ctor(String portName, int baudRate, Parity parity,
                           int dataBits, StopBits stopbits);

  private native void detr();

  public SerialPort(String portName, int baudRate, Parity parity, int dataBits,
                    StopBits stopBits) {
    ctor(portName, baudRate, parity, dataBits, stopBits);
  }

  protected void finalize() throws Throwable {
    detr();
    super.finalize();
  }

  public SerialPort(String portName, int baudRate, Parity parity, int dataBits) {
    this(portName, baudRate, parity, dataBits, StopBits.One);
  }

  public SerialPort(String portName, int baudRate, Parity parity) {
    this(portName, baudRate, parity, 8);
  }

  public SerialPort(String portName, int baudRate) {
    this(portName, baudRate, Parity.None);
  }

  public SerialPort(String portName) {
    this(portName, 9600);
  }

  public SerialPort() {
    this("");
  }

  public native void setBaudRate(int value) throws IOException,
          NullPointerException;

  public native void setDataBits(int value) throws IOException,
          NullPointerException;

  public native void setHandshake(Handshake value) throws IOException,
          NullPointerException;

  public native void setParity(Parity value) throws IOException,
          NullPointerException;

  public native void setPortName(String value) throws IOException,
          NullPointerException;

  public native void setStopBits(StopBits value) throws IOException,
          NullPointerException;

  public native void setReadBufferSize(int value) throws IOException,
          NullPointerException;

  public native void setWriteBufferSize(int value) throws IOException,
          NullPointerException;

  public native void setReadTimeout(int value) throws IOException,
          NullPointerException;

  public native void setWriteTimeout(int value) throws IOException,
          NullPointerException;

  public native void setDtrEnable(boolean value) throws IOException,
          NullPointerException;

  public native void setRtsEnable(boolean value) throws IOException,
          NullPointerException;

  public native int bytesToWrite() throws IOException, NullPointerException;

  public native int bytesToRead() throws IOException, NullPointerException;

  public native boolean isOpen() throws NullPointerException;

  public native void close() throws IOException, NullPointerException;

  public native void open() throws IOException, NullPointerException;

  public native void discardInBuffer() throws IOException, NullPointerException;

  public native void discardOutBuffer() throws IOException, NullPointerException;

  public native void flush() throws IOException, NullPointerException;

  public native int read(byte[] buffer, int offset, int count) throws IOException, NullPointerException, TimeoutException;

  public native void write(byte[] buffer, int offset, int count) throws IOException, NullPointerException, TimeoutException;

  public native static String[] getPortNames();
//  public static String[] getPortNames() {
//    final Pattern re = Pattern.compile("tty*");
//    String[] ttys = new File("/dev/").list(new FilenameFilter() {
//      @Override
//      public boolean accept(File dir, String name) {
//        return new File(dir, name).isFile() && re.matcher(name).matches();
//      }
//    });
//
//    if (ttys == null) return null;
//
//    ArrayList<String> serialPorts = new ArrayList<String>();
//    boolean linuxStyle = false;
//    //
//    // Probe for linux-styled devices : /dev/ttyS* or /dev/ttyUSB*
//    //
//    for (String dev : ttys) {
//      if (dev.startsWith("/dev/ttyS") || dev.startsWith("/dev/ttyUSB")) {
//        linuxStyle = true;
//        break;
//      }
//    }
//
//    for (String dev : ttys) {
//      if (linuxStyle) {
//        if (dev.startsWith("/dev/ttyS") || dev.startsWith("/dev/ttyUSB")) {
//          serialPorts.add(dev);
//        } else {
//          if (dev != "/dev/tty" && dev.startsWith("/dev/tty") && !dev.startsWith("/dev/ttyC"))
//            serialPorts.add(dev);
//        }
//      }
//    }
//
//    return serialPorts.toArray(new String[serialPorts.size()]);
//  }
}

