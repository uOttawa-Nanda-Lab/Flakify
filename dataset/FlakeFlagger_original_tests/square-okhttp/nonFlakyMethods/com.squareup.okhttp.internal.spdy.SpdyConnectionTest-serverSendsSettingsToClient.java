@Test public void serverSendsSettingsToClient() throws Exception {
  Settings settings=new Settings();
  settings.set(Settings.MAX_CONCURRENT_STREAMS,PERSIST_VALUE,10);
  peer.sendFrame().settings(settings);
  peer.sendFrame().ping(false,2,0);
  peer.acceptFrame();
  peer.play();
  SpdyConnection connection=connection(peer,SPDY3);
  peer.takeFrame();
synchronized (connection) {
    assertEquals(10,connection.peerSettings.getMaxConcurrentStreams(-1));
  }
}
