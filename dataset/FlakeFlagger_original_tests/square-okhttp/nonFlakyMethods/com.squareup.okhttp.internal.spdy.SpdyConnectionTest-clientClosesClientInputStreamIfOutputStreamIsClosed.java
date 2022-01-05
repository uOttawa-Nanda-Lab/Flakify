/** 
 * Test that the client doesn't send a RST_STREAM if doing so will disrupt the output stream.
 */
@Test public void clientClosesClientInputStreamIfOutputStreamIsClosed() throws Exception {
  peer.acceptFrame();
  peer.acceptFrame();
  peer.acceptFrame();
  peer.acceptFrame();
  peer.play();
  SpdyConnection connection=connection(peer,SPDY3);
  SpdyStream stream=connection.newStream(headerEntries("a","android"),true,true);
  Source source=stream.getSource();
  BufferedSink out=Okio.buffer(stream.getSink());
  source.close();
  try {
    source.read(new OkBuffer(),1);
    fail();
  }
 catch (  IOException expected) {
    assertEquals("stream closed",expected.getMessage());
  }
  out.writeUtf8("square");
  out.flush();
  out.close();
  assertEquals(0,connection.openStreamCount());
  MockSpdyPeer.InFrame synStream=peer.takeFrame();
  assertEquals(TYPE_HEADERS,synStream.type);
  assertEquals(HeadersMode.SPDY_SYN_STREAM,synStream.headersMode);
  assertFalse(synStream.inFinished);
  assertFalse(synStream.outFinished);
  MockSpdyPeer.InFrame data=peer.takeFrame();
  assertEquals(TYPE_DATA,data.type);
  assertTrue(Arrays.equals("square".getBytes("UTF-8"),data.data));
  MockSpdyPeer.InFrame fin=peer.takeFrame();
  assertEquals(TYPE_DATA,fin.type);
  assertTrue(fin.inFinished);
  assertFalse(fin.outFinished);
  MockSpdyPeer.InFrame rstStream=peer.takeFrame();
  assertEquals(TYPE_RST_STREAM,rstStream.type);
  assertEquals(CANCEL,rstStream.errorCode);
}
