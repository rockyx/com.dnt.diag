package com.dnt.diag.io;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created with IntelliJ IDEA.
 * User: Rocky Tsui
 * Date: 13-12-2
 * Time: 下午3:48
 * To change this template use File | Settings | File Templates.
 */
public class SerialPortThread {
  class SerialPortRead implements Runnable {
    private SerialPort _port;
    private IOBuffer _buffer;
    private byte[] _buff;
    private int _size;

    protected SerialPortRead(SerialPort port, IOBuffer buffer) {
      _port = port;
      _buffer = buffer;
      _buff = new byte[256];
      _size = 0;
    }

    @Override
    public void run() {
      try {
        while (_port.isOpen()) {
          try {
            _size = _port.bytesToRead();
            while (_size > 0) {
              _size = _size < 256 ? _size : 256;
              _size = _port.read(_buff, 0, _size);
              _buffer.pushToFromEcuBuffer(_buff, 0, _size);
              _size = _port.bytesToRead();
            }
            Thread.sleep(1);
          } catch (TimeoutException ex) {
            ex.printStackTrace();
          } catch (InterruptedException ex) {
            ex.printStackTrace();
          } catch (IOException ex) {
            ex.printStackTrace();
          }
        }
      } catch (NullPointerException ex) {
        ex.printStackTrace();
      }
    }
  }

  class SerialPortWrite implements Runnable {
    private SerialPort _port;
    private IOBuffer _buffer;
    private byte[] _buff;
    private int _size;

    protected SerialPortWrite(SerialPort port, IOBuffer buffer) {
      _port = port;
      _buffer = buffer;
      _buff = new byte[256];
      _size = 0;
    }

    @Override
    public void run() {
      try {
        while (_port.isOpen()) {
          try {
            _size = _buffer.toEcuBufferBytesAvailable();
            while (_size > 0) {
              _size = _size < 256 ? _size : 256;
              _buffer.popFromToEcuBuffer(_buff, 0, _size);
              _port.write(_buff, 0, _size);
              _size = _buffer.toEcuBufferBytesAvailable();
            }

            Thread.sleep(1);
          } catch (TimeoutException ex) {
            ex.printStackTrace();
          } catch (InterruptedException ex) {
            ex.printStackTrace();
          } catch (IOException ex) {
            ex.printStackTrace();
          }
        }
      } catch (NullPointerException ex) {
        ex.printStackTrace();
      }
    }
  }

  SerialPort _port;
  IOBuffer _buffer;
  SerialPortRead _read;
  SerialPortWrite _write;
  ExecutorService _executor;

  public SerialPortThread(IOBuffer buffer) {
    _port = null;
    _buffer = buffer;
    _read = null;
    _write = null;
    _executor = Executors.newCachedThreadPool();
  }

  protected void finalize() throws Throwable {
    if (_port != null) {
      _port.close();
    }
    _executor.awaitTermination(2, TimeUnit.SECONDS);
    super.finalize();
  }

  public void open(String portName, int baudRate, SerialPort.Parity parity, int dataBits,
                   SerialPort.StopBits stopBits) throws IOException {
    _port = new SerialPort(portName, baudRate, parity, dataBits, stopBits);
    try {
      _port.open();
    } catch (NullPointerException e) {
      e.printStackTrace();
      throw new IOException();
    } catch (IOException e) {
      _port.close();
      throw e;
    }
  }

  public void start() {
    _read = new SerialPortRead(_port, _buffer);
    _write = new SerialPortWrite(_port, _buffer);
    _executor.execute(_read);
    _executor.execute(_write);
  }

  public SerialPort getPort() {
    return _port;
  }

  public void stop() {
    try {
      _port.close();
      _executor.awaitTermination(2, TimeUnit.SECONDS);
    } catch (Exception ex) {

    }
  }
}
