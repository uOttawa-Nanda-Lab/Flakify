@Test public void close() throws Exception {
  peer.acceptFrame();
  peer.acceptFrame();
  peer.acceptFrame();
  peer.play();
  SpdyConnection connection=connection(peer,SPDY3);
  SpdyStream stream=connection.newStream(headerEntries("a","android"),true,true);
  assertEquals(1,connection.openStreamCount());
  connection.close();
  assertEquals(0,connection.openStreamCount());
  try {
    connection.newStream(headerEntries("b","banana"),true,true);
    fail();
  }
 catch (  IOException expected) {
    assertEquals("shutdown",expected.getMessage());
  }
  BufferedSink sink=Okio.buffer(stream.getSink());
  try {
    sink.writeByte(0);
    sink.flush();
    fail();
  }
 catch (  IOException expected) {
    assertEquals("stream was reset: CANCEL",expected.getMessage());
  }
  try {
    stream.getSource().read(new OkBuffer(),1);
    fail();
  }
 catch (  IOException expected) {
    assertEquals("stream was reset: CANCEL",expected.getMessage());
  }
  MockSpdyPeer.InFrame synStream=peer.takeFrame();
  assertEquals(TYPE_HEADERS,synStream.type);
  assertEquals(HeadersMode.SPDY_SYN_STREAM,synStream.headersMode);
  MockSpdyPeer.InFrame goaway=peer.takeFrame();
  assertEquals(TYPE_GOAWAY,goaway.type);
  MockSpdyPeer.InFrame rstStream=peer.takeFrame();
  assertEquals(TYPE_RST_STREAM,rstStream.type);
  assertEquals(1,rstStream.streamId);
}
