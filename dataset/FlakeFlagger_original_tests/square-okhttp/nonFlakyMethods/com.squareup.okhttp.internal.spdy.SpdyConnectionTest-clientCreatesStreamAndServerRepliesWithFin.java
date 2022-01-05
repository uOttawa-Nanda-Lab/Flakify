@Test public void clientCreatesStreamAndServerRepliesWithFin() throws Exception {
  peer.acceptFrame();
  peer.acceptFrame();
  peer.sendFrame().synReply(true,1,headerEntries("a","android"));
  peer.sendFrame().ping(true,1,0);
  peer.play();
  SpdyConnection connection=connection(peer,SPDY3);
  connection.newStream(headerEntries("b","banana"),false,true);
  assertEquals(1,connection.openStreamCount());
  connection.ping().roundTripTime();
  assertEquals(0,connection.openStreamCount());
  MockSpdyPeer.InFrame synStream=peer.takeFrame();
  assertEquals(TYPE_HEADERS,synStream.type);
  assertEquals(HeadersMode.SPDY_SYN_STREAM,synStream.headersMode);
  MockSpdyPeer.InFrame ping=peer.takeFrame();
  assertEquals(TYPE_PING,ping.type);
}
