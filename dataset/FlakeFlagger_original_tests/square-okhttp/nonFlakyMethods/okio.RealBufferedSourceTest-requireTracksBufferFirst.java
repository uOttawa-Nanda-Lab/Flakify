@Test public void requireTracksBufferFirst() throws Exception {
  OkBuffer source=new OkBuffer();
  source.writeUtf8("bb");
  BufferedSource bufferedSource=new RealBufferedSource(source);
  bufferedSource.buffer().writeUtf8("aa");
  bufferedSource.require(2);
  assertEquals(2,bufferedSource.buffer().size());
  assertEquals(2,source.size());
}
