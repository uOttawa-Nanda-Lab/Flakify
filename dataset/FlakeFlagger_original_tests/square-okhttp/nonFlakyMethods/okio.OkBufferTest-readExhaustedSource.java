@Test public void readExhaustedSource() throws Exception {
  OkBuffer sink=new OkBuffer();
  sink.writeUtf8(repeat('a',10));
  OkBuffer source=new OkBuffer();
  assertEquals(-1,source.read(sink,10));
  assertEquals(10,sink.size());
  assertEquals(0,source.size());
}
