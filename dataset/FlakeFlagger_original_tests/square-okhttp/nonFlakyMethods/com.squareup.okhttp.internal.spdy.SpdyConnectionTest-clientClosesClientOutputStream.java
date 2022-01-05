@Test public void clientClosesClientOutputStream() throws Exception {
  peer.acceptFrame();
  peer.sendFrame().synReply(false,1,headerEntries("b","banana"));
  peer.acceptFrame();
  peer.acceptFrame();
  peer.acceptFrame();
  peer.sendFrame().ping(true,1,0);
  peer.play();
  SpdyConnection connection=connection(peer,SPDY3);
  SpdyStream stream=connection.newStream(headerEntries("a","android"),true,false);
  BufferedSink out=Okio.buffer(stream.getSink());
  out.writeUtf8("square");
  out.flush();
  assertEquals(1,connection.openStreamCount());
  out.close();
  try {
    out.writeUtf8("round");
    fail();
  }
 catch (  Exception expected) {
    assertEquals("closed",expected.getMessage());
  }
  connection.ping().roundTripTime();
  assertEquals(0,connection.openStreamCount());
  MockSpdyPeer.InFrame synStream=peer.takeFrame();
  assertEquals(TYPE_HEADERS,synStream.type);
  assertEquals(HeadersMode.SPDY_SYN_STREAM,synStream.headersMode);
  assertFalse(synStream.inFinished);
  assertTrue(synStream.outFinished);
  MockSpdyPeer.InFrame data=peer.takeFrame();
  assertEquals(TYPE_DATA,data.type);
  assertFalse(data.inFinished);
  assertTrue(Arrays.equals("square".getBytes("UTF-8"),data.data));
  MockSpdyPeer.InFrame fin=peer.takeFrame();
  assertEquals(TYPE_DATA,fin.type);
  assertTrue(fin.inFinished);
  MockSpdyPeer.InFrame ping=peer.takeFrame();
  assertEquals(TYPE_PING,ping.type);
  assertEquals(1,ping.payload1);
}
