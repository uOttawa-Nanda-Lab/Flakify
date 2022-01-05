@Test public void goAwayWithoutDebugDataRoundTrip() throws IOException {
  OkBuffer frame=new OkBuffer();
  final ErrorCode expectedError=ErrorCode.PROTOCOL_ERROR;
  frame.writeShort(8);
  frame.writeByte(Http20Draft09.TYPE_GOAWAY);
  frame.writeByte(0);
  frame.writeInt(0);
  frame.writeInt(expectedStreamId);
  frame.writeInt(expectedError.httpCode);
  assertEquals(frame,sendGoAway(expectedStreamId,expectedError,Util.EMPTY_BYTE_ARRAY));
  FrameReader fr=new Http20Draft09.Reader(frame,4096,false);
  fr.nextFrame(new BaseTestHandler(){
    @Override public void goAway(    int lastGoodStreamId,    ErrorCode errorCode,    ByteString debugData){
      assertEquals(expectedStreamId,lastGoodStreamId);
      assertEquals(expectedError,errorCode);
      assertEquals(0,debugData.size());
    }
  }
);
}
