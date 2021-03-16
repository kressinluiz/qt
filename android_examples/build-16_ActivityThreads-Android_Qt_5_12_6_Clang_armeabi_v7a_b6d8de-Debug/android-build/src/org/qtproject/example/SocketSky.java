package org.qtproject.example;

import com.skydroid.fpvlibrary.socket.SocketConnection;
//import com.skydroid.fpvlibrary.socket.SocketControl;

public class SocketSky {
  private SocketConnection mSocketConnection;

  public SocketSky(SocketConnection connection) {
    this.mSocketConnection = connection;
  }

  public void sendMessage(byte[] msg) {
    if (this.mSocketConnection != null) {
      this.mSocketConnection.sendData(msg);
    }
  }

}
