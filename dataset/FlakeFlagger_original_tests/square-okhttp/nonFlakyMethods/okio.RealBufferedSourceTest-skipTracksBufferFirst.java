@Test public void skipTracksBufferFirst() throws Exception {
  OkBuffer source=new OkBuffer();
  source.writeUtf8("bb");
  BufferedSource bufferedSource=new RealBufferedSource(source);
  bufferedSource.buffer().writeUtf8("aa");
  bufferedSource.skip(2);
  assertEquals(0,bufferedSource.buffer().size());
  assertEquals(2,source.size());
}
