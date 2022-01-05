@Test public void bufferedSinkEmitZero() throws IOException {
  OkBuffer sink=new OkBuffer();
  BufferedSink bufferedSink=new RealBufferedSink(sink);
  bufferedSink.writeUtf8("");
  assertEquals(0,sink.size());
}
