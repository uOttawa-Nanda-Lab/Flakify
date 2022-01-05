@Test public void bytesEmittedToSinkWithFlush() throws Exception {
  OkBuffer sink=new OkBuffer();
  BufferedSink bufferedSink=new RealBufferedSink(sink);
  bufferedSink.writeUtf8("abc");
  bufferedSink.flush();
  assertEquals(3,sink.size());
}
