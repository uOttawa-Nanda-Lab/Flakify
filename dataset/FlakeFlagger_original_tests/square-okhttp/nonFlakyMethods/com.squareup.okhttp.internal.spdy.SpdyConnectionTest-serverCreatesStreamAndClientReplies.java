@Test public void serverCreatesStreamAndClientReplies() throws Exception {
  final List<Header> pushHeaders=headerEntries(":scheme","https",":host","localhost:8888",":method","GET",":path","/index.html",":status","200",":version","HTTP/1.1","content-type","text/html");
  peer.sendFrame().synStream(false,false,2,0,5,129,pushHeaders);
  peer.acceptFrame();
  peer.play();
  final AtomicInteger receiveCount=new AtomicInteger();
  IncomingStreamHandler handler=new IncomingStreamHandler(){
    @Override public void receive(    SpdyStream stream) throws IOException {
      receiveCount.incrementAndGet();
      assertEquals(pushHeaders,stream.getRequestHeaders());
      assertEquals(null,stream.getErrorCode());
      assertEquals(5,stream.getPriority());
      stream.reply(headerEntries("b","banana"),true);
    }
  }
;
  new SpdyConnection.Builder(true,peer.openSocket()).handler(handler).build();
  MockSpdyPeer.InFrame reply=peer.takeFrame();
  assertEquals(TYPE_HEADERS,reply.type);
  assertEquals(HeadersMode.SPDY_REPLY,reply.headersMode);
  assertFalse(reply.inFinished);
  assertEquals(2,reply.streamId);
  assertEquals(headerEntries("b","banana"),reply.headerBlock);
  assertEquals(1,receiveCount.get());
}
