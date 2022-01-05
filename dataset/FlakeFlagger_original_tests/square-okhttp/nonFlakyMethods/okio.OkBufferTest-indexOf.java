@Test public void indexOf() throws Exception {
  OkBuffer buffer=new OkBuffer();
  assertEquals(-1,buffer.indexOf((byte)'a'));
  buffer.writeUtf8("a");
  assertEquals(0,buffer.indexOf((byte)'a'));
  assertEquals(-1,buffer.indexOf((byte)'b'));
  buffer.writeUtf8(repeat('b',Segment.SIZE - 2));
  assertEquals(0,buffer.indexOf((byte)'a'));
  assertEquals(1,buffer.indexOf((byte)'b'));
  assertEquals(-1,buffer.indexOf((byte)'c'));
  buffer.readUtf8(2);
  assertEquals(-1,buffer.indexOf((byte)'a'));
  assertEquals(0,buffer.indexOf((byte)'b'));
  assertEquals(-1,buffer.indexOf((byte)'c'));
  buffer.writeUtf8("c");
  assertEquals(-1,buffer.indexOf((byte)'a'));
  assertEquals(0,buffer.indexOf((byte)'b'));
  assertEquals(Segment.SIZE - 3,buffer.indexOf((byte)'c'));
  buffer.readUtf8(2);
  assertEquals(-1,buffer.indexOf((byte)'a'));
  assertEquals(0,buffer.indexOf((byte)'b'));
  assertEquals(Segment.SIZE - 5,buffer.indexOf((byte)'c'));
  buffer.writeUtf8("d");
  assertEquals(asList(Segment.SIZE - 4,1),buffer.segmentSizes());
  assertEquals(Segment.SIZE - 4,buffer.indexOf((byte)'d'));
  assertEquals(-1,buffer.indexOf((byte)'e'));
}
