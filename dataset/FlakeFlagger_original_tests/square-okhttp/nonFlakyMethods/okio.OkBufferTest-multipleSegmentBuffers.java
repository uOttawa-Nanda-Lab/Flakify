@Test public void multipleSegmentBuffers() throws Exception {
  OkBuffer buffer=new OkBuffer();
  buffer.writeUtf8(repeat('a',1000));
  buffer.writeUtf8(repeat('b',2500));
  buffer.writeUtf8(repeat('c',5000));
  buffer.writeUtf8(repeat('d',10000));
  buffer.writeUtf8(repeat('e',25000));
  buffer.writeUtf8(repeat('f',50000));
  assertEquals(repeat('a',999),buffer.readUtf8(999));
  assertEquals("a" + repeat('b',2500) + "c",buffer.readUtf8(2502));
  assertEquals(repeat('c',4998),buffer.readUtf8(4998));
  assertEquals("c" + repeat('d',10000) + "e",buffer.readUtf8(10002));
  assertEquals(repeat('e',24998),buffer.readUtf8(24998));
  assertEquals("e" + repeat('f',50000),buffer.readUtf8(50001));
  assertEquals(0,buffer.size());
}
