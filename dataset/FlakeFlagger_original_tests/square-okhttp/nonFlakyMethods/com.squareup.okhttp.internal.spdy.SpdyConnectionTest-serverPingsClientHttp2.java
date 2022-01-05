@Test public void serverPingsClientHttp2() throws Exception {
  peer.setVariantAndClient(HTTP_20_DRAFT_09,false);
  peer.sendFrame().ping(false,2,3);
  peer.acceptFrame();
  peer.play();
  connection(peer,HTTP_20_DRAFT_09);
  MockSpdyPeer.InFrame ping=peer.takeFrame();
  assertEquals(TYPE_PING,ping.type);
  assertEquals(0,ping.streamId);
  assertEquals(2,ping.payload1);
  assertEquals(3,ping.payload2);
  assertTrue(ping.ack);
}
