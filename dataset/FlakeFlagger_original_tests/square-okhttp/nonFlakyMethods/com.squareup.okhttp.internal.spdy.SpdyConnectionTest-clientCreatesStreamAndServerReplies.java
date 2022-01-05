@Test public void clientCreatesStreamAndServerReplies() throws Exception {
  peer.acceptFrame();
  peer.sendFrame().synReply(false,1,headerEntries("a","android"));
  peer.sendFrame().data(true,1,new OkBuffer().writeUtf8("robot"));
  peer.acceptFrame();
  peer.play();
  SpdyConnection connection=connection(peer,SPDY3);
  SpdyStream stream=connection.newStream(headerEntries("b","banana"),true,true);
  assertEquals(headerEntries("a","android"),stream.getResponseHeaders());
  assertStreamData("robot",stream.getSource());
  BufferedSink out=Okio.buffer(stream.getSink());
  out.writeUtf8("c3po");
  out.close();
  assertEquals(0,connection.openStreamCount());
  MockSpdyPeer.InFrame synStream=peer.takeFrame();
  assertEquals(TYPE_HEADERS,synStream.type);
  assertEquals(HeadersMode.SPDY_SYN_STREAM,synStream.headersMode);
  assertFalse(synStream.inFinished);
  assertFalse(synStream.outFinished);
  assertEquals(1,synStream.streamId);
  assertEquals(0,synStream.associatedStreamId);
  assertEquals(headerEntries("b","banana"),synStream.headerBlock);
  MockSpdyPeer.InFrame requestData=peer.takeFrame();
  assertTrue(Arrays.equals("c3po".getBytes("UTF-8"),requestData.data));
}
