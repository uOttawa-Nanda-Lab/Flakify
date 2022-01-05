@Test public void writePrefixDoesntSplit() throws Exception {
  OkBuffer sink=new OkBuffer();
  sink.writeUtf8(repeat('b',10));
  OkBuffer source=new OkBuffer();
  source.writeUtf8(repeat('a',Segment.SIZE * 2));
  sink.write(source,20);
  assertEquals(asList(30),sink.segmentSizes());
  assertEquals(asList(Segment.SIZE - 20,Segment.SIZE),source.segmentSizes());
  assertEquals(30,sink.size());
  assertEquals(Segment.SIZE * 2 - 20,source.size());
}
