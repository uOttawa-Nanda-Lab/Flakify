@Test public void serverPingsClient() throws Exception {
  peer.sendFrame().ping(false,2,0);
  peer.acceptFrame();
  peer.play();
  connection(peer,SPDY3);
  MockSpdyPeer.InFrame ping=peer.takeFrame();
  assertEquals(0,ping.streamId);
  assertEquals(2,ping.payload1);
  assertEquals(0,ping.payload2);
  assertTrue(ping.ack);
}
