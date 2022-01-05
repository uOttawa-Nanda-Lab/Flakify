@Test public void bufferedSinkEmitsTailWhenItIsComplete() throws IOException {
  OkBuffer sink=new OkBuffer();
  BufferedSink bufferedSink=new RealBufferedSink(sink);
  bufferedSink.writeUtf8(repeat('a',Segment.SIZE - 1));
  assertEquals(0,sink.size());
  bufferedSink.writeByte(0);
  assertEquals(Segment.SIZE,sink.size());
  assertEquals(0,bufferedSink.buffer().size());
}
