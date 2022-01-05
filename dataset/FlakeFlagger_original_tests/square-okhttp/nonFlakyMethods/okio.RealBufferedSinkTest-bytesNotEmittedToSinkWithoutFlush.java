@Test public void bytesNotEmittedToSinkWithoutFlush() throws Exception {
  OkBuffer sink=new OkBuffer();
  BufferedSink bufferedSink=new RealBufferedSink(sink);
  bufferedSink.writeUtf8("abc");
  assertEquals(0,sink.size());
}
