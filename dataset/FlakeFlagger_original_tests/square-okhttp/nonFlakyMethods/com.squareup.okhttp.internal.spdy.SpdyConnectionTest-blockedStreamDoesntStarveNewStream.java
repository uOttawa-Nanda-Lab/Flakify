@Test public void blockedStreamDoesntStarveNewStream() throws Exception {
  int framesThatFillWindow=roundUp(INITIAL_WINDOW_SIZE,SPDY3.maxFrameSize());
  peer.acceptFrame();
  for (int i=0; i < framesThatFillWindow; i++) {
    peer.acceptFrame();
  }
  peer.acceptFrame();
  peer.acceptFrame();
  peer.play();
  SpdyConnection connection=connection(peer,SPDY3);
  SpdyStream stream1=connection.newStream(headerEntries("a","apple"),true,true);
  BufferedSink out1=Okio.buffer(stream1.getSink());
  out1.write(new byte[INITIAL_WINDOW_SIZE]);
  out1.flush();
  assertEquals(0,connection.bytesLeftInWriteWindow);
  assertEquals(0,connection.getStream(1).bytesLeftInWriteWindow);
  connection.readerRunnable.windowUpdate(0,3);
  assertEquals(3,connection.bytesLeftInWriteWindow);
  assertEquals(0,connection.getStream(1).bytesLeftInWriteWindow);
  SpdyStream stream2=connection.newStream(headerEntries("b","banana"),true,true);
  BufferedSink out2=Okio.buffer(stream2.getSink());
  out2.writeUtf8("foo");
  out2.flush();
  assertEquals(0,connection.bytesLeftInWriteWindow);
  assertEquals(0,connection.getStream(1).bytesLeftInWriteWindow);
  assertEquals(INITIAL_WINDOW_SIZE - 3,connection.getStream(3).bytesLeftInWriteWindow);
}
