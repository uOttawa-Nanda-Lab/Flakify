@Test public void bufferedSinkFlush() throws IOException {
  OkBuffer sink=new OkBuffer();
  BufferedSink bufferedSink=new RealBufferedSink(sink);
  bufferedSink.writeByte('a');
  assertEquals(0,sink.size());
  bufferedSink.flush();
  assertEquals(0,bufferedSink.buffer().size());
  assertEquals(1,sink.size());
}
