@Test public void initialSettingsWithWindowSizeAdjustsConnection() throws Exception {
  int framesThatFillWindow=roundUp(INITIAL_WINDOW_SIZE,HTTP_20_DRAFT_09.maxFrameSize());
  peer.acceptFrame();
  for (int i=0; i < framesThatFillWindow; i++) {
    peer.acceptFrame();
  }
  peer.acceptFrame();
  peer.play();
  SpdyConnection connection=connection(peer,SPDY3);
  SpdyStream stream=connection.newStream(headerEntries("a","apple"),true,true);
  BufferedSink out=Okio.buffer(stream.getSink());
  out.write(new byte[INITIAL_WINDOW_SIZE]);
  out.flush();
  out.writeByte('a');
  assertFlushBlocks(out);
  assertEquals(0,connection.bytesLeftInWriteWindow);
  assertEquals(0,connection.getStream(1).bytesLeftInWriteWindow);
  Settings initial=new Settings();
  initial.set(Settings.INITIAL_WINDOW_SIZE,PERSIST_VALUE,INITIAL_WINDOW_SIZE + 1);
  connection.readerRunnable.settings(false,initial);
  assertEquals(1,connection.bytesLeftInWriteWindow);
  assertEquals(1,connection.getStream(1).bytesLeftInWriteWindow);
  out.flush();
  assertEquals(0,connection.bytesLeftInWriteWindow);
  assertEquals(0,connection.getStream(1).bytesLeftInWriteWindow);
  Settings next=new Settings();
  next.set(Settings.INITIAL_WINDOW_SIZE,PERSIST_VALUE,INITIAL_WINDOW_SIZE + 2);
  connection.readerRunnable.settings(false,next);
  assertEquals(0,connection.bytesLeftInWriteWindow);
  assertEquals(1,connection.getStream(1).bytesLeftInWriteWindow);
}
