@Test public void byteAt() throws Exception {
  OkBuffer buffer=new OkBuffer();
  buffer.writeUtf8("a");
  buffer.writeUtf8(repeat('b',Segment.SIZE));
  buffer.writeUtf8("c");
  assertEquals('a',buffer.getByte(0));
  assertEquals('a',buffer.getByte(0));
  assertEquals('c',buffer.getByte(buffer.size - 1));
  assertEquals('b',buffer.getByte(buffer.size - 2));
  assertEquals('b',buffer.getByte(buffer.size - 3));
}
