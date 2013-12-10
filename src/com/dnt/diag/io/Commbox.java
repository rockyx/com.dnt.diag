package com.dnt.diag.io;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * Created with IntelliJ IDEA.
 * User: Rocky Tsui
 * Date: 13-12-2
 * Time: 下午4:36
 * To change this template use File | Settings | File Templates.
 */
public class Commbox {
  static {
    System.loadLibrary("dntdiag");
  }

  public static final int UNKNOW = 0;
  public static final int C168 = 1;
  public static final int W80 = 2;
  public static final int ELM327 = 3;
  public static final int TL718 = 4;
  public static final int LAST = 5;

  private long _ptr;
  private IOBuffer _stream = null;
  private int _ver = UNKNOW;
  private SerialPortThread _port;

  private native void ctor(long buffNative, int ver);

  private native void detr();

  private native void c168BeginBaudChange() throws ClassCastException, IOException;

  private native void c168EndBaudChange() throws ClassCastException, IOException;

  public native void nativeConnect() throws IOException;

  public native void nativeDisconnect() throws IOException;

  public Commbox(int ver) {
    _stream = new IOBuffer();
    ctor(_stream.getNative(), ver);
    _ver = ver;
    _port = new SerialPortThread(_stream);
  }

  protected void finalize() throws Throwable {
    if (_port != null) {
      _port.stop();
      _port = null;
    }
    detr();
    super.finalize();
  }

  public int getVer() {
    return _ver;
  }

  private void openC168SerialMode() throws IOException {
    _port.stop();
    String[] infos = SerialPort.getPortNames();

    if (infos == null)
      throw new IOException();

    for (String info : infos) {
      try {
        _port.open(info, 9600, SerialPort.Parity.None, 8, SerialPort.StopBits.One);
        SerialPort port = _port.getPort();
        port.setDtrEnable(false);
        Thread.sleep(50);
        port.setDtrEnable(true);
        Thread.sleep(50);

        _port.start();

        for (int i = 0; i < 3; i++) {
          try {
            nativeConnect();
            c168BeginBaudChange();
            port.setBaudRate(57600);
            c168EndBaudChange();
            return;
          } catch (Exception ex) {
            continue;
          }
        }

      } catch (InterruptedException ex) {
        ex.printStackTrace();
      } catch (ClassCastException ex) {
        ex.printStackTrace();
        throw new IOException();
      }
    }
    throw new IOException();
  }

  private void openW80SerialMode() throws IOException {
    _port.stop();
    String[] infos = SerialPort.getPortNames();

    for (String info : infos) {
      try {
        _port.open(info, 115200, SerialPort.Parity.None, 8, SerialPort.StopBits.Two);

        SerialPort port = _port.getPort();
        port.setDtrEnable(false);
        Thread.sleep(50);
        port.setDtrEnable(true);
        Thread.sleep(50);

        _port.start();

        for (int i = 0; i < 3; i++) {
          try {
            nativeConnect();
            return;
          } catch (Exception ex) {
            continue;
          }
        }
      } catch (InterruptedException ex) {
        ex.printStackTrace();
      }
    }
  }

  public void connect() throws IOException {
    if (_ver == C168) {
      openC168SerialMode(); // C168 Only support SerialPort.
    } else if (_ver == W80) {
      openW80SerialMode(); // W80 Only support SerialPort.
    } else if (_ver == ELM327) {
      throw new UnsupportedOperationException();
    } else if (_ver == TL718) {
      throw new UnsupportedOperationException();
    } else {
      throw new NoSuchElementException();
    }
  }

  public void disconnect() throws IOException {
    nativeDisconnect();
    if (_ver == C168 || _ver == W80) {
      _port.stop();
      _port = null;
    }
  }
}
