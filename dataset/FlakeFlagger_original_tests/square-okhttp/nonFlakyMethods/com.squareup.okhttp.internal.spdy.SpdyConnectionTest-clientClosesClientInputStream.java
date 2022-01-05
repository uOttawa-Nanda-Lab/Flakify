/** 
 * Test that the client sends a RST_STREAM if doing so won't disrupt the output stream.
 */
@Test public void clientClosesClientInputStream() throws Exception {
  peer.acceptFrame();
  peer.acceptFrame();
  peer.play();
  SpdyConnection connection=connection(peer,SPDY3);
  SpdyStream stream=connection.newStream(headerEntries("a","android"),false,true);
  Source in=stream.getSource();
  BufferedSink out=Okio.buffer(stream.getSink());
  in.close();
  try {
    in.read(new OkBuffer(),1);
    fail();
  }
 catch (  IOException expected) {
    assertEquals("stream closed",expected.getMessage());
  }
  try {
    out.writeUtf8("a");
    out.flush();
    fail();
  }
 catch (  IOException expected) {
    assertEquals("stream finished",expected.getMessage());
  }
  assertEquals(0,connection.openStreamCount());
  MockSpdyPeer.InFrame synStream=peer.takeFrame();
  assertEquals(TYPE_HEADERS,synStream.type);
  assertEquals(HeadersMode.SPDY_SYN_STREAM,synStream.headersMode);
  assertTrue(synStream.inFinished);
  assertFalse(synStream.outFinished);
  MockSpdyPeer.InFrame rstStream=peer.takeFrame();
  assertEquals(TYPE_RST_STREAM,rstStream.type);
  assertEquals(CANCEL,rstStream.errorCode);
}
