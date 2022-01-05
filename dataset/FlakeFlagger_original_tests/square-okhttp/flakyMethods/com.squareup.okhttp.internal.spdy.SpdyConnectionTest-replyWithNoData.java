@Test public void replyWithNoData() throws Exception {
  peer.sendFrame().synStream(false,false,2,0,0,0,headerEntries("a","android"));
  peer.acceptFrame();
  peer.play();
  final AtomicInteger receiveCount=new AtomicInteger();
  IncomingStreamHandler handler=new IncomingStreamHandler(){
    @Override public void receive(    SpdyStream stream) throws IOException {
      stream.reply(headerEntries("b","banana"),false);
      receiveCount.incrementAndGet();
    }
  }
;
  connectionBuilder(peer,SPDY3).handler(handler).build();
  MockSpdyPeer.InFrame reply=peer.takeFrame();
  assertEquals(TYPE_HEADERS,reply.type);
  assertTrue(reply.inFinished);
  assertEquals(headerEntries("b","banana"),reply.headerBlock);
  assertEquals(1,receiveCount.get());
  assertEquals(HeadersMode.SPDY_REPLY,reply.headersMode);
}
