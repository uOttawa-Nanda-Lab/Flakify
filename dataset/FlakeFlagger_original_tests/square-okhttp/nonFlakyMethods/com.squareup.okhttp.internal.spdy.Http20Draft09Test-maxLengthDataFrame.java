@Test public void maxLengthDataFrame() throws IOException {
  OkBuffer frame=new OkBuffer();
  final byte[] expectedData=new byte[16383];
  Arrays.fill(expectedData,(byte)2);
  frame.writeShort(expectedData.length);
  frame.writeByte(Http20Draft09.TYPE_DATA);
  frame.writeByte(0);
  frame.writeInt(expectedStreamId & 0x7fffffff);
  frame.write(expectedData);
  assertEquals(frame,sendDataFrame(new OkBuffer().write(expectedData)));
  FrameReader fr=new Http20Draft09.Reader(frame,4096,false);
  fr.nextFrame(new BaseTestHandler(){
    @Override public void data(    boolean inFinished,    int streamId,    BufferedSource source,    int length) throws IOException {
      assertFalse(inFinished);
      assertEquals(expectedStreamId,streamId);
      assertEquals(16383,length);
      ByteString data=source.readByteString(length);
      for (      byte b : data.toByteArray()) {
        assertEquals(2,b);
      }
    }
  }
);
}
