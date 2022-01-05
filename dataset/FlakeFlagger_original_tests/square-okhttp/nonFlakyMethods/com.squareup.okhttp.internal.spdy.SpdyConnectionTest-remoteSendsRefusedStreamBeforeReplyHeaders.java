@Test public void remoteSendsRefusedStreamBeforeReplyHeaders() throws Exception {
  peer.acceptFrame();
  peer.sendFrame().rstStream(1,REFUSED_STREAM);
  peer.sendFrame().ping(false,2,0);
  peer.acceptFrame();
  peer.play();
  SpdyConnection connection=connection(peer,SPDY3);
  SpdyStream stream=connection.newStream(headerEntries("a","android"),true,true);
  try {
    stream.getResponseHeaders();
    fail();
  }
 catch (  IOException expected) {
    assertEquals("stream was reset: REFUSED_STREAM",expected.getMessage());
  }
  assertEquals(0,connection.openStreamCount());
  MockSpdyPeer.InFrame synStream=peer.takeFrame();
  assertEquals(TYPE_HEADERS,synStream.type);
  assertEquals(HeadersMode.SPDY_SYN_STREAM,synStream.headersMode);
  MockSpdyPeer.InFrame ping=peer.takeFrame();
  assertEquals(TYPE_PING,ping.type);
  assertEquals(2,ping.payload1);
}
