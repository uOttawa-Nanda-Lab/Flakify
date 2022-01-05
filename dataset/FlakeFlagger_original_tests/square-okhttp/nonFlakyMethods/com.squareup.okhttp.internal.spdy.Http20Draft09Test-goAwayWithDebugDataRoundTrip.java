@Test public void goAwayWithDebugDataRoundTrip() throws IOException {
  OkBuffer frame=new OkBuffer();
  final ErrorCode expectedError=ErrorCode.PROTOCOL_ERROR;
  final ByteString expectedData=ByteString.encodeUtf8("abcdefgh");
  frame.writeShort(8 + expectedData.size());
  frame.writeByte(Http20Draft09.TYPE_GOAWAY);
  frame.writeByte(0);
  frame.writeInt(0);
  frame.writeInt(0);
  frame.writeInt(expectedError.httpCode);
  frame.write(expectedData.toByteArray());
  assertEquals(frame,sendGoAway(0,expectedError,expectedData.toByteArray()));
  FrameReader fr=new Http20Draft09.Reader(frame,4096,false);
  fr.nextFrame(new BaseTestHandler(){
    @Override public void goAway(    int lastGoodStreamId,    ErrorCode errorCode,    ByteString debugData){
      assertEquals(0,lastGoodStreamId);
      assertEquals(expectedError,errorCode);
      assertEquals(expectedData,debugData);
    }
  }
);
}
