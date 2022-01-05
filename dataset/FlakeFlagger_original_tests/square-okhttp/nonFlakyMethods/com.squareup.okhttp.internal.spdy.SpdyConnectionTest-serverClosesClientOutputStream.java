@Test public void serverClosesClientOutputStream() throws Exception {
  peer.acceptFrame();
  peer.sendFrame().rstStream(1,CANCEL);
  peer.acceptFrame();
  peer.sendFrame().ping(true,1,0);
  peer.play();
  SpdyConnection connection=connection(peer,SPDY3);
  SpdyStream stream=connection.newStream(headerEntries("a","android"),true,true);
  BufferedSink out=Okio.buffer(stream.getSink());
  connection.ping().roundTripTime();
  try {
    out.writeUtf8("square");
    out.flush();
    fail();
  }
 catch (  IOException expected) {
    assertEquals("stream was reset: CANCEL",expected.getMessage());
  }
  try {
    out.close();
    fail();
  }
 catch (  IOException expected) {
  }
  assertEquals(0,connection.openStreamCount());
  MockSpdyPeer.InFrame synStream=peer.takeFrame();
  assertEquals(TYPE_HEADERS,synStream.type);
  assertEquals(HeadersMode.SPDY_SYN_STREAM,synStream.headersMode);
  assertFalse(synStream.inFinished);
  assertFalse(synStream.outFinished);
  MockSpdyPeer.InFrame ping=peer.takeFrame();
  assertEquals(TYPE_PING,ping.type);
  assertEquals(1,ping.payload1);
}
