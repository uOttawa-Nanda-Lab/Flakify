@Test public void windowUpdateRoundTrip() throws IOException {
  OkBuffer frame=new OkBuffer();
  final long expectedWindowSizeIncrement=0x7fffffff;
  frame.writeShort(4);
  frame.writeByte(Http20Draft09.TYPE_WINDOW_UPDATE);
  frame.writeByte(0);
  frame.writeInt(expectedStreamId);
  frame.writeInt((int)expectedWindowSizeIncrement);
  assertEquals(frame,windowUpdate(expectedWindowSizeIncrement));
  FrameReader fr=new Http20Draft09.Reader(frame,4096,false);
  fr.nextFrame(new BaseTestHandler(){
    @Override public void windowUpdate(    int streamId,    long windowSizeIncrement){
      assertEquals(expectedStreamId,streamId);
      assertEquals(expectedWindowSizeIncrement,windowSizeIncrement);
    }
  }
);
}
