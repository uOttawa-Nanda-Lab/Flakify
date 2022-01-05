@Test public void serverClosesClientInputStream() throws Exception {
  peer.acceptFrame();
  peer.sendFrame().synReply(false,1,headerEntries("b","banana"));
  peer.sendFrame().data(true,1,new OkBuffer().writeUtf8("square"));
  peer.play();
  SpdyConnection connection=connection(peer,SPDY3);
  SpdyStream stream=connection.newStream(headerEntries("a","android"),false,true);
  Source source=stream.getSource();
  assertStreamData("square",source);
  assertEquals(0,connection.openStreamCount());
  MockSpdyPeer.InFrame synStream=peer.takeFrame();
  assertEquals(TYPE_HEADERS,synStream.type);
  assertEquals(HeadersMode.SPDY_SYN_STREAM,synStream.headersMode);
  assertTrue(synStream.inFinished);
  assertFalse(synStream.outFinished);
}
