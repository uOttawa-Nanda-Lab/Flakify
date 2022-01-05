@Test public void closeEmitsBufferedBytes() throws IOException {
  OkBuffer sink=new OkBuffer();
  BufferedSink bufferedSink=new RealBufferedSink(sink);
  bufferedSink.writeByte('a');
  bufferedSink.close();
  assertEquals('a',sink.readByte());
}
