@Test public void readShortSplitAcrossMultipleSegments() throws Exception {
  OkBuffer data=new OkBuffer();
  data.writeUtf8(repeat('a',Segment.SIZE - 1));
  data.write(new byte[]{(byte)0xab,(byte)0xcd});
  data.readUtf8(Segment.SIZE - 1);
  assertEquals((short)0xabcd,data.readShort());
  assertEquals(0,data.size());
}
