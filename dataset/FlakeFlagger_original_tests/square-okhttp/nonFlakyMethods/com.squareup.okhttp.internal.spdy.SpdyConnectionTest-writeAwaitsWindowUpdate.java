@Test public void writeAwaitsWindowUpdate() throws Exception {
  int framesThatFillWindow=roundUp(INITIAL_WINDOW_SIZE,HTTP_20_DRAFT_09.maxFrameSize());
  peer.acceptFrame();
  for (int i=0; i < framesThatFillWindow; i++) {
    peer.acceptFrame();
  }
  peer.acceptFrame();
  peer.play();
  SpdyConnection connection=connection(peer,SPDY3);
  SpdyStream stream=connection.newStream(headerEntries("b","banana"),true,true);
  BufferedSink out=Okio.buffer(stream.getSink());
  out.write(new byte[INITIAL_WINDOW_SIZE]);
  out.flush();
  assertEquals(0,connection.bytesLeftInWriteWindow);
  assertEquals(0,connection.getStream(1).bytesLeftInWriteWindow);
  out.writeByte('a');
  assertFlushBlocks(out);
  connection.readerRunnable.windowUpdate(0,1);
  assertFlushBlocks(out);
  connection.readerRunnable.windowUpdate(1,1);
  out.flush();
  MockSpdyPeer.InFrame synStream=peer.takeFrame();
  assertEquals(TYPE_HEADERS,synStream.type);
  for (int i=0; i < framesThatFillWindow; i++) {
    MockSpdyPeer.InFrame data=peer.takeFrame();
    assertEquals(TYPE_DATA,data.type);
  }
}
