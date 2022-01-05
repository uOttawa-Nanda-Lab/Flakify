@Test public void bufferedSinkEmitMultipleSegments() throws IOException {
  OkBuffer sink=new OkBuffer();
  BufferedSink bufferedSink=new RealBufferedSink(sink);
  bufferedSink.writeUtf8(repeat('a',Segment.SIZE * 4 - 1));
  assertEquals(Segment.SIZE * 3,sink.size());
  assertEquals(Segment.SIZE - 1,bufferedSink.buffer().size());
}
