@Test public void requireReadsOneSegmentAtATime() throws Exception {
  OkBuffer source=new OkBuffer();
  source.writeUtf8(repeat('a',Segment.SIZE));
  source.writeUtf8(repeat('b',Segment.SIZE));
  BufferedSource bufferedSource=new RealBufferedSource(source);
  bufferedSource.require(2);
  assertEquals(Segment.SIZE,source.size());
  assertEquals(Segment.SIZE,bufferedSource.buffer().size());
}
