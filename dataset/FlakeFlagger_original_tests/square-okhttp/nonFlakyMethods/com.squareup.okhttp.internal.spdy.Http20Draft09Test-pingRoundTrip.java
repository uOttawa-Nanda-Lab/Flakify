@Test public void pingRoundTrip() throws IOException {
  OkBuffer frame=new OkBuffer();
  final int expectedPayload1=7;
  final int expectedPayload2=8;
  frame.writeShort(8);
  frame.writeByte(Http20Draft09.TYPE_PING);
  frame.writeByte(Http20Draft09.FLAG_ACK);
  frame.writeInt(0);
  frame.writeInt(expectedPayload1);
  frame.writeInt(expectedPayload2);
  assertEquals(frame,sendPingFrame(true,expectedPayload1,expectedPayload2));
  FrameReader fr=new Http20Draft09.Reader(frame,4096,false);
  fr.nextFrame(new BaseTestHandler(){
    @Override public void ping(    boolean ack,    int payload1,    int payload2){
      assertTrue(ack);
      assertEquals(expectedPayload1,payload1);
      assertEquals(expectedPayload2,payload2);
    }
  }
);
}
