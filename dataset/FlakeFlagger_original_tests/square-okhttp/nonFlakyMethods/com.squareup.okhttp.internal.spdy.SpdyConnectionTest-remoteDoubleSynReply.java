@Test public void remoteDoubleSynReply() throws Exception {
  peer.acceptFrame();
  peer.sendFrame().synReply(false,1,headerEntries("a","android"));
  peer.acceptFrame();
  peer.sendFrame().synReply(false,1,headerEntries("b","banana"));
  peer.sendFrame().ping(true,1,0);
  peer.acceptFrame();
  peer.play();
  SpdyConnection connection=connection(peer,SPDY3);
  SpdyStream stream=connection.newStream(headerEntries("c","cola"),true,true);
  assertEquals(headerEntries("a","android"),stream.getResponseHeaders());
  connection.ping().roundTripTime();
  try {
    stream.getSource().read(new OkBuffer(),1);
    fail();
  }
 catch (  IOException expected) {
    assertEquals("stream was reset: STREAM_IN_USE",expected.getMessage());
  }
  MockSpdyPeer.InFrame synStream=peer.takeFrame();
  assertEquals(TYPE_HEADERS,synStream.type);
  assertEquals(HeadersMode.SPDY_SYN_STREAM,synStream.headersMode);
  MockSpdyPeer.InFrame ping=peer.takeFrame();
  assertEquals(TYPE_PING,ping.type);
  MockSpdyPeer.InFrame rstStream=peer.takeFrame();
  assertEquals(TYPE_RST_STREAM,rstStream.type);
  assertEquals(1,rstStream.streamId);
  assertEquals(STREAM_IN_USE,rstStream.errorCode);
}
