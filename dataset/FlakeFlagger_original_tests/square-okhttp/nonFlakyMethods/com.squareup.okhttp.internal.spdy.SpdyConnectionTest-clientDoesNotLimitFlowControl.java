@Test public void clientDoesNotLimitFlowControl() throws Exception {
  peer.acceptFrame();
  peer.sendFrame().synReply(false,1,headerEntries("b","banana"));
  peer.sendFrame().data(false,1,new OkBuffer().write(new byte[64 * 1024 + 1]));
  peer.sendFrame().ping(false,2,0);
  peer.acceptFrame();
  peer.play();
  SpdyConnection connection=connection(peer,SPDY3);
  SpdyStream stream=connection.newStream(headerEntries("a","android"),true,true);
  assertEquals(headerEntries("b","banana"),stream.getResponseHeaders());
  MockSpdyPeer.InFrame synStream=peer.takeFrame();
  assertEquals(TYPE_HEADERS,synStream.type);
  assertEquals(HeadersMode.SPDY_SYN_STREAM,synStream.headersMode);
  MockSpdyPeer.InFrame ping=peer.takeFrame();
  assertEquals(TYPE_PING,ping.type);
  assertEquals(2,ping.payload1);
}
