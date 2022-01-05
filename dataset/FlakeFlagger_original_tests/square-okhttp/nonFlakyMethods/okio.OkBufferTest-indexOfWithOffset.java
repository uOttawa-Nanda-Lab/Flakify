@Test public void indexOfWithOffset() throws Exception {
  OkBuffer buffer=new OkBuffer();
  int halfSegment=Segment.SIZE / 2;
  buffer.writeUtf8(repeat('a',halfSegment));
  buffer.writeUtf8(repeat('b',halfSegment));
  buffer.writeUtf8(repeat('c',halfSegment));
  buffer.writeUtf8(repeat('d',halfSegment));
  assertEquals(0,buffer.indexOf((byte)'a',0));
  assertEquals(halfSegment - 1,buffer.indexOf((byte)'a',halfSegment - 1));
  assertEquals(halfSegment,buffer.indexOf((byte)'b',halfSegment - 1));
  assertEquals(halfSegment * 2,buffer.indexOf((byte)'c',halfSegment - 1));
  assertEquals(halfSegment * 3,buffer.indexOf((byte)'d',halfSegment - 1));
  assertEquals(halfSegment * 3,buffer.indexOf((byte)'d',halfSegment * 2));
  assertEquals(halfSegment * 3,buffer.indexOf((byte)'d',halfSegment * 3));
  assertEquals(halfSegment * 4 - 1,buffer.indexOf((byte)'d',halfSegment * 4 - 1));
}
