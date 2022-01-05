@Test public void headersBeforeReply() throws Exception {
  peer.acceptFrame();
  peer.acceptFrame();
  peer.sendFrame().headers(1,headerEntries("c","c3po"));
  peer.acceptFrame();
  peer.sendFrame().ping(true,1,0);
  peer.play();
  SpdyConnection connection=connection(peer,SPDY3);
  SpdyStream stream=connection.newStream(headerEntries("b","banana"),true,true);
  connection.ping().roundTripTime();
  try {
    stream.getResponseHeaders();
    fail();
  }
 catch (  IOException expected) {
    assertEquals("stream was reset: PROTOCOL_ERROR",expected.getMessage());
  }
  MockSpdyPeer.InFrame synStream=peer.takeFrame();
  assertEquals(TYPE_HEADERS,synStream.type);
  assertEquals(HeadersMode.SPDY_SYN_STREAM,synStream.headersMode);
  MockSpdyPeer.InFrame ping=peer.takeFrame();
  assertEquals(TYPE_PING,ping.type);
  MockSpdyPeer.InFrame rstStream=peer.takeFrame();
  assertEquals(TYPE_RST_STREAM,rstStream.type);
  assertEquals(PROTOCOL_ERROR,rstStream.errorCode);
}
