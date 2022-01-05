@Test public void inputStreamFromSource() throws Exception {
  OkBuffer source=new OkBuffer();
  source.writeUtf8("a");
  source.writeUtf8(repeat('b',Segment.SIZE));
  source.writeUtf8("c");
  InputStream in=new RealBufferedSource(source).inputStream();
  assertEquals(0,in.available());
  assertEquals(Segment.SIZE + 2,source.size());
  assertEquals('a',in.read());
  assertEquals(Segment.SIZE - 1,in.available());
  assertEquals(2,source.size());
  byte[] data=new byte[Segment.SIZE * 2];
  assertEquals(Segment.SIZE - 1,in.read(data,0,data.length));
  assertEquals(repeat('b',Segment.SIZE - 1),new String(data,0,Segment.SIZE - 1,UTF_8));
  assertEquals(2,source.size());
  assertEquals('b',in.read());
  assertEquals(1,in.available());
  assertEquals(0,source.size());
  assertEquals('c',in.read());
  assertEquals(0,in.available());
  assertEquals(0,source.size());
  assertEquals(-1,in.read());
  assertEquals(0,source.size());
}
