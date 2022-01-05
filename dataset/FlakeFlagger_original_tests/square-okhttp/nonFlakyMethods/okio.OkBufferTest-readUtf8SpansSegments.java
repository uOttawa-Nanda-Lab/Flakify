@Test public void readUtf8SpansSegments() throws Exception {
  OkBuffer buffer=new OkBuffer();
  buffer.writeUtf8(repeat('a',Segment.SIZE * 2));
  buffer.readUtf8(Segment.SIZE - 1);
  assertEquals("aa",buffer.readUtf8(2));
}
