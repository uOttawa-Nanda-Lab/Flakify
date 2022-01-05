@Test public void unexpectedPingIsNotReturned() throws Exception {
  peer.sendFrame().ping(false,2,0);
  peer.acceptFrame();
  peer.sendFrame().ping(true,3,0);
  peer.sendFrame().ping(false,4,0);
  peer.acceptFrame();
  peer.play();
  connection(peer,SPDY3);
  MockSpdyPeer.InFrame ping2=peer.takeFrame();
  assertEquals(2,ping2.payload1);
  MockSpdyPeer.InFrame ping4=peer.takeFrame();
  assertEquals(4,ping4.payload1);
}
