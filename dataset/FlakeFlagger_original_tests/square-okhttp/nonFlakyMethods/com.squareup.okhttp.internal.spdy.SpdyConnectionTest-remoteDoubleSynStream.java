@Test public void remoteDoubleSynStream() throws Exception {
  peer.sendFrame().synStream(false,false,2,0,0,0,headerEntries("a","android"));
  peer.acceptFrame();
  peer.sendFrame().synStream(false,false,2,0,0,0,headerEntries("b","banana"));
  peer.acceptFrame();
  peer.play();
  final AtomicInteger receiveCount=new AtomicInteger();
  IncomingStreamHandler handler=new IncomingStreamHandler(){
    @Override public void receive(    SpdyStream stream) throws IOException {
      receiveCount.incrementAndGet();
      assertEquals(headerEntries("a","android"),stream.getRequestHeaders());
      assertEquals(null,stream.getErrorCode());
      stream.reply(headerEntries("c","cola"),true);
    }
  }
;
  new SpdyConnection.Builder(true,peer.openSocket()).handler(handler).build();
  MockSpdyPeer.InFrame reply=peer.takeFrame();
  assertEquals(TYPE_HEADERS,reply.type);
  assertEquals(HeadersMode.SPDY_REPLY,reply.headersMode);
  MockSpdyPeer.InFrame rstStream=peer.takeFrame();
  assertEquals(TYPE_RST_STREAM,rstStream.type);
  assertEquals(2,rstStream.streamId);
  assertEquals(PROTOCOL_ERROR,rstStream.errorCode);
  assertEquals(1,receiveCount.intValue());
}
