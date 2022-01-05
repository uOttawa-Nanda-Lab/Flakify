@Test public void testTruncatedDataFrame() throws Exception {
  peer.acceptFrame();
  peer.sendFrame().synReply(false,1,headerEntries("a","android"));
  peer.sendTruncatedFrame(8 + 100).data(false,1,data(1024));
  peer.play();
  SpdyConnection connection=connection(peer,SPDY3);
  SpdyStream stream=connection.newStream(headerEntries("b","banana"),true,true);
  assertEquals(headerEntries("a","android"),stream.getResponseHeaders());
  Source in=stream.getSource();
  try {
    Okio.buffer(in).readByteString(101);
    fail();
  }
 catch (  IOException expected) {
    assertEquals("stream was reset: PROTOCOL_ERROR",expected.getMessage());
  }
}
