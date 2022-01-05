@Test public void writeLastByteInSegment() throws Exception {
  OkBuffer data=new OkBuffer();
  data.writeUtf8(repeat('a',Segment.SIZE - 1));
  data.writeByte(0x20);
  data.writeByte(0x21);
  assertEquals(asList(Segment.SIZE,1),data.segmentSizes());
  assertEquals(repeat('a',Segment.SIZE - 1),data.readUtf8(Segment.SIZE - 1));
  assertEquals("OkBuffer[size=2 data=2021]",data.toString());
}
