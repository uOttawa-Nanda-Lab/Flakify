@Test public void peerHttp2ServerLowersInitialWindowSize() throws Exception {
  peer.setVariantAndClient(HTTP_20_DRAFT_09,false);
  Settings initial=new Settings();
  initial.set(Settings.INITIAL_WINDOW_SIZE,PERSIST_VALUE,1684);
  Settings shouldntImpactConnection=new Settings();
  shouldntImpactConnection.set(Settings.INITIAL_WINDOW_SIZE,PERSIST_VALUE,3368);
  peer.sendFrame().settings(initial);
  peer.acceptFrame();
  peer.sendFrame().settings(shouldntImpactConnection);
  peer.acceptFrame();
  peer.acceptFrame();
  peer.play();
  SpdyConnection connection=connection(peer,HTTP_20_DRAFT_09);
  MockSpdyPeer.InFrame ackFrame=peer.takeFrame();
  assertEquals(TYPE_SETTINGS,ackFrame.type);
  assertEquals(0,ackFrame.streamId);
  assertTrue(ackFrame.ack);
  ackFrame=peer.takeFrame();
  assertEquals(TYPE_SETTINGS,ackFrame.type);
  assertEquals(0,ackFrame.streamId);
  assertTrue(ackFrame.ack);
  SpdyStream stream=connection.newStream(headerEntries("a","android"),false,true);
  assertEquals(3368,connection.peerSettings.getInitialWindowSize());
  assertEquals(1684,connection.bytesLeftInWriteWindow);
  assertEquals(3368,stream.bytesLeftInWriteWindow);
}
