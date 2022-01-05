@Test public void bogusDataFrameDoesNotDisruptConnection() throws Exception {
  peer.sendFrame().data(true,41,new OkBuffer().writeUtf8("bogus"));
  peer.acceptFrame();
  peer.sendFrame().ping(false,2,0);
  peer.acceptFrame();
  peer.play();
  connection(peer,SPDY3);
  MockSpdyPeer.InFrame rstStream=peer.takeFrame();
  assertEquals(TYPE_RST_STREAM,rstStream.type);
  assertEquals(41,rstStream.streamId);
  assertEquals(INVALID_STREAM,rstStream.errorCode);
  MockSpdyPeer.InFrame ping=peer.takeFrame();
  assertEquals(2,ping.payload1);
}
