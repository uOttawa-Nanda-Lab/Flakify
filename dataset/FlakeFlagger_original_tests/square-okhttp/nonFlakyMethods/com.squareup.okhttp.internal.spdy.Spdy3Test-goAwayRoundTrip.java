@Test public void goAwayRoundTrip() throws IOException {
  OkBuffer frame=new OkBuffer();
  final ErrorCode expectedError=ErrorCode.PROTOCOL_ERROR;
  frame.writeInt(0x80000000 | (Spdy3.VERSION & 0x7fff) << 16 | Spdy3.TYPE_GOAWAY & 0xffff);
  frame.writeInt(8);
  frame.writeInt(expectedStreamId);
  frame.writeInt(expectedError.spdyGoAwayCode);
  assertEquals(frame,sendGoAway(expectedStreamId,expectedError,Util.EMPTY_BYTE_ARRAY));
  assertEquals(frame,sendGoAway(expectedStreamId,expectedError,new byte[8]));
  FrameReader fr=new Spdy3.Reader(frame,false);
  fr.nextFrame(new BaseTestHandler(){
    @Override public void goAway(    int lastGoodStreamId,    ErrorCode errorCode,    ByteString debugData){
      assertEquals(expectedStreamId,lastGoodStreamId);
      assertEquals(expectedError,errorCode);
      assertEquals(0,debugData.size());
    }
  }
);
}
