@Test public void multipleSettingsFramesAreMerged() throws Exception {
  Settings settings1=new Settings();
  settings1.set(Settings.UPLOAD_BANDWIDTH,PERSIST_VALUE,100);
  settings1.set(Settings.DOWNLOAD_BANDWIDTH,PERSIST_VALUE,200);
  settings1.set(Settings.DOWNLOAD_RETRANS_RATE,0,300);
  peer.sendFrame().settings(settings1);
  Settings settings2=new Settings();
  settings2.set(Settings.DOWNLOAD_BANDWIDTH,0,400);
  settings2.set(Settings.DOWNLOAD_RETRANS_RATE,PERSIST_VALUE,500);
  settings2.set(Settings.MAX_CONCURRENT_STREAMS,PERSIST_VALUE,600);
  peer.sendFrame().settings(settings2);
  peer.sendFrame().ping(false,2,0);
  peer.acceptFrame();
  peer.play();
  SpdyConnection connection=connection(peer,SPDY3);
  peer.takeFrame();
synchronized (connection) {
    assertEquals(100,connection.peerSettings.getUploadBandwidth(-1));
    assertEquals(PERSIST_VALUE,connection.peerSettings.flags(Settings.UPLOAD_BANDWIDTH));
    assertEquals(400,connection.peerSettings.getDownloadBandwidth(-1));
    assertEquals(0,connection.peerSettings.flags(Settings.DOWNLOAD_BANDWIDTH));
    assertEquals(500,connection.peerSettings.getDownloadRetransRate(-1));
    assertEquals(PERSIST_VALUE,connection.peerSettings.flags(Settings.DOWNLOAD_RETRANS_RATE));
    assertEquals(600,connection.peerSettings.getMaxConcurrentStreams(-1));
    assertEquals(PERSIST_VALUE,connection.peerSettings.flags(Settings.MAX_CONCURRENT_STREAMS));
  }
}
