@Test public void completeSegmentsEmitted() throws Exception {
  OkBuffer sink=new OkBuffer();
  BufferedSink bufferedSink=new RealBufferedSink(sink);
  bufferedSink.writeUtf8(repeat('a',Segment.SIZE * 3));
  assertEquals(Segment.SIZE * 3,sink.size());
}
