@Test public void skipReadsOneSegmentAtATime() throws Exception {
  OkBuffer source=new OkBuffer();
  source.writeUtf8(repeat('a',Segment.SIZE));
  source.writeUtf8(repeat('b',Segment.SIZE));
  BufferedSource bufferedSource=new RealBufferedSource(source);
  bufferedSource.skip(2);
  assertEquals(Segment.SIZE,source.size());
  assertEquals(Segment.SIZE - 2,bufferedSource.buffer().size());
}
