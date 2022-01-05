@Test public void headers() throws Exception {
  peer.acceptFrame();
  peer.acceptFrame();
  peer.sendFrame().synReply(false,1,headerEntries("a","android"));
  peer.sendFrame().headers(1,headerEntries("c","c3po"));
  peer.sendFrame().ping(true,1,0);
  peer.play();
  SpdyConnection connection=connection(peer,SPDY3);
  SpdyStream stream=connection.newStream(headerEntries("b","banana"),true,true);
  connection.ping().roundTripTime();
  assertEquals(headerEntries("a","android","c","c3po"),stream.getResponseHeaders());
  MockSpdyPeer.InFrame synStream=peer.takeFrame();
  assertEquals(TYPE_HEADERS,synStream.type);
  assertEquals(HeadersMode.SPDY_SYN_STREAM,synStream.headersMode);
  MockSpdyPeer.InFrame ping=peer.takeFrame();
  assertEquals(TYPE_PING,ping.type);
}
