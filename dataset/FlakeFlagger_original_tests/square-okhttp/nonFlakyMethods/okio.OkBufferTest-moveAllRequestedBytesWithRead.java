@Test public void moveAllRequestedBytesWithRead() throws Exception {
  OkBuffer sink=new OkBuffer();
  sink.writeUtf8(repeat('a',10));
  OkBuffer source=new OkBuffer();
  source.writeUtf8(repeat('b',15));
  assertEquals(10,source.read(sink,10));
  assertEquals(20,sink.size());
  assertEquals(5,source.size());
  assertEquals(repeat('a',10) + repeat('b',10),sink.readUtf8(20));
}
