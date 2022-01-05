@Test public void peerHttp2ServerZerosCompressionTable() throws Exception {
  boolean client=false;
  Settings settings=new Settings();
  settings.set(Settings.HEADER_TABLE_SIZE,PERSIST_VALUE,0);
  SpdyConnection connection=sendHttp2SettingsAndCheckForAck(client,settings);
synchronized (connection) {
    assertEquals(0,connection.peerSettings.getHeaderTableSize());
    Http20Draft09.Reader frameReader=(Http20Draft09.Reader)connection.frameReader;
    assertEquals(0,frameReader.hpackReader.maxHeaderTableByteCount());
  }
}
