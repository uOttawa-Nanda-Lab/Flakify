@Test public void unknownFrameTypeIgnored() throws IOException {
  OkBuffer frame=new OkBuffer();
  frame.writeShort(4);
  frame.writeByte(99);
  frame.writeByte(0);
  frame.writeInt(expectedStreamId);
  frame.writeInt(111111111);
  FrameReader fr=new Http20Draft09.Reader(frame,4096,false);
  fr.nextFrame(new BaseTestHandler());
}
