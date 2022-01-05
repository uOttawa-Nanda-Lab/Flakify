@Test public void closeCancelsPings() throws Exception {
  peer.acceptFrame();
  peer.acceptFrame();
  peer.play();
  SpdyConnection connection=connection(peer,SPDY3);
  Ping ping=connection.ping();
  connection.close();
  assertEquals(-1,ping.roundTripTime());
}
