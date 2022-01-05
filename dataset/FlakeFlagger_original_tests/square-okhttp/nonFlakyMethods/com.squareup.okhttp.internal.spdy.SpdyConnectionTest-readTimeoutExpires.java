@Test public void readTimeoutExpires() throws Exception {
  peer.acceptFrame();
  peer.sendFrame().synReply(false,1,headerEntries("a","android"));
  peer.acceptFrame();
  peer.sendFrame().ping(true,1,0);
  peer.play();
  SpdyConnection connection=connection(peer,SPDY3);
  SpdyStream stream=connection.newStream(headerEntries("b","banana"),true,true);
  stream.setReadTimeout(1000);
  Source source=stream.getSource();
  long startNanos=System.nanoTime();
  try {
    source.read(new OkBuffer(),1);
    fail();
  }
 catch (  IOException expected) {
  }
  long elapsedNanos=System.nanoTime() - startNanos;
  assertEquals(1000d,TimeUnit.NANOSECONDS.toMillis(elapsedNanos),200d);
  assertEquals(1,connection.openStreamCount());
  connection.ping().roundTripTime();
  MockSpdyPeer.InFrame synStream=peer.takeFrame();
  assertEquals(TYPE_HEADERS,synStream.type);
}
