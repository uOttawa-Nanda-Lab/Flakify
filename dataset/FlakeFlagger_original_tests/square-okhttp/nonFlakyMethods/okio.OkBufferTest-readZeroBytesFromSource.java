@Test public void readZeroBytesFromSource() throws Exception {
  OkBuffer sink=new OkBuffer();
  sink.writeUtf8(repeat('a',10));
  OkBuffer source=new OkBuffer();
  assertEquals(-1,source.read(sink,0));
  assertEquals(10,sink.size());
  assertEquals(0,source.size());
}
