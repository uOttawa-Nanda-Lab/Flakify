@Test public void readIntSplitAcrossMultipleSegments() throws Exception {
  OkBuffer data=new OkBuffer();
  data.writeUtf8(repeat('a',Segment.SIZE - 3));
  data.write(new byte[]{(byte)0xab,(byte)0xcd,(byte)0xef,(byte)0x01});
  data.readUtf8(Segment.SIZE - 3);
  assertEquals(0xabcdef01,data.readInt());
  assertEquals(0,data.size());
}
