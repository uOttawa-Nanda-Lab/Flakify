@Test public void requireIncludesBufferBytes() throws Exception {
  OkBuffer source=new OkBuffer();
  source.writeUtf8("b");
  BufferedSource bufferedSource=new RealBufferedSource(source);
  bufferedSource.buffer().writeUtf8("a");
  bufferedSource.require(2);
  assertEquals("ab",bufferedSource.buffer().readUtf8(2));
}
