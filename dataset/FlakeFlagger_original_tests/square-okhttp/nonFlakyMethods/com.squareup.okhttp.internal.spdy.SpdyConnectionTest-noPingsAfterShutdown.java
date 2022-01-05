@Test public void noPingsAfterShutdown() throws Exception {
  peer.acceptFrame();
  peer.play();
  SpdyConnection connection=connection(peer,SPDY3);
  connection.shutdown(INTERNAL_ERROR);
  try {
    connection.ping();
    fail();
  }
 catch (  IOException expected) {
    assertEquals("shutdown",expected.getMessage());
  }
  MockSpdyPeer.InFrame goaway=peer.takeFrame();
  assertEquals(TYPE_GOAWAY,goaway.type);
  assertEquals(INTERNAL_ERROR,goaway.errorCode);
}
