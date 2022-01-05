@Test public void readUtf8EntireBuffer() throws Exception {
  OkBuffer buffer=new OkBuffer();
  buffer.writeUtf8(repeat('a',Segment.SIZE));
  assertEquals(repeat('a',Segment.SIZE),buffer.readUtf8(Segment.SIZE));
}
