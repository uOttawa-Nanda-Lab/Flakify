@Test public void remoteSendsDataAfterInFinished() throws Exception {
  peer.acceptFrame();
  peer.sendFrame().synReply(false,1,headerEntries("a","android"));
  peer.sendFrame().data(true,1,new OkBuffer().writeUtf8("robot"));
  peer.sendFrame().data(true,1,new OkBuffer().writeUtf8("c3po"));
  peer.sendFrame().ping(false,2,0);
  peer.acceptFrame();
  peer.play();
  SpdyConnection connection=connection(peer,SPDY3);
  SpdyStream stream=connection.newStream(headerEntries("b","banana"),true,true);
  assertEquals(headerEntries("a","android"),stream.getResponseHeaders());
  assertStreamData("robot",stream.getSource());
  MockSpdyPeer.InFrame synStream=peer.takeFrame();
  assertEquals(TYPE_HEADERS,synStream.type);
  assertEquals(HeadersMode.SPDY_SYN_STREAM,synStream.headersMode);
  MockSpdyPeer.InFrame ping=peer.takeFrame();
  assertEquals(TYPE_PING,ping.type);
  assertEquals(2,ping.payload1);
}
