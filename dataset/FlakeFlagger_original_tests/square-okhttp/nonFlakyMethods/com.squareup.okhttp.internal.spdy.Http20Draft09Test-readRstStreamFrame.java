@Test public void readRstStreamFrame() throws IOException {
  OkBuffer frame=new OkBuffer();
  frame.writeShort(4);
  frame.writeByte(Http20Draft09.TYPE_RST_STREAM);
  frame.writeByte(0);
  frame.writeInt(expectedStreamId & 0x7fffffff);
  frame.writeInt(ErrorCode.COMPRESSION_ERROR.httpCode);
  FrameReader fr=new Http20Draft09.Reader(frame,4096,false);
  fr.nextFrame(new BaseTestHandler(){
    @Override public void rstStream(    int streamId,    ErrorCode errorCode){
      assertEquals(expectedStreamId,streamId);
      assertEquals(ErrorCode.COMPRESSION_ERROR,errorCode);
    }
  }
);
}
