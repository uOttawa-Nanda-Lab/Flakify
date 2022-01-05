@Test public void sourceFromInputStream() throws Exception {
  InputStream in=new ByteArrayInputStream(("a" + repeat('b',Segment.SIZE * 2) + "c").getBytes(UTF_8));
  Source source=Okio.source(in);
  OkBuffer sink=new OkBuffer();
  assertEquals(3,source.read(sink,3));
  assertEquals("abb",sink.readUtf8(3));
  assertEquals(Segment.SIZE,source.read(sink,20000));
  assertEquals(repeat('b',Segment.SIZE),sink.readUtf8(sink.size()));
  assertEquals(Segment.SIZE - 1,source.read(sink,20000));
  assertEquals(repeat('b',Segment.SIZE - 2) + "c",sink.readUtf8(sink.size()));
  assertEquals(-1,source.read(sink,1));
}
