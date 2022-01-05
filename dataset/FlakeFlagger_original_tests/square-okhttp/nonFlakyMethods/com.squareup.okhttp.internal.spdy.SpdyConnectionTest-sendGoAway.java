@Test public void sendGoAway() throws Exception {
  peer.acceptFrame();
  peer.acceptFrame();
  peer.acceptFrame();
  peer.sendFrame().synStream(false,false,2,0,0,0,headerEntries("b","b"));
  peer.sendFrame().ping(true,1,0);
  peer.play();
  SpdyConnection connection=connection(peer,SPDY3);
  connection.newStream(headerEntries("a","android"),true,true);
  Ping ping=connection.ping();
  connection.shutdown(PROTOCOL_ERROR);
  assertEquals(1,connection.openStreamCount());
  ping.roundTripTime();
  MockSpdyPeer.InFrame synStream1=peer.takeFrame();
  assertEquals(TYPE_HEADERS,synStream1.type);
  MockSpdyPeer.InFrame pingFrame=peer.takeFrame();
  assertEquals(TYPE_PING,pingFrame.type);
  MockSpdyPeer.InFrame goaway=peer.takeFrame();
  assertEquals(TYPE_GOAWAY,goaway.type);
  assertEquals(0,goaway.streamId);
  assertEquals(PROTOCOL_ERROR,goaway.errorCode);
}
