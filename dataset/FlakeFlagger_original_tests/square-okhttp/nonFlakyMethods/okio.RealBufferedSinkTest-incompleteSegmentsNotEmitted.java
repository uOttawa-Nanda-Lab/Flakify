@Test public void incompleteSegmentsNotEmitted() throws Exception {
  OkBuffer sink=new OkBuffer();
  BufferedSink bufferedSink=new RealBufferedSink(sink);
  bufferedSink.writeUtf8(repeat('a',Segment.SIZE * 3 - 1));
  assertEquals(Segment.SIZE * 2,sink.size());
}
