@Test public void skip() throws Exception {
  OkBuffer buffer=new OkBuffer();
  buffer.writeUtf8("a");
  buffer.writeUtf8(repeat('b',Segment.SIZE));
  buffer.writeUtf8("c");
  buffer.skip(1);
  assertEquals('b',buffer.readByte() & 0xff);
  buffer.skip(Segment.SIZE - 2);
  assertEquals('b',buffer.readByte() & 0xff);
  buffer.skip(1);
  assertEquals(0,buffer.size());
}
