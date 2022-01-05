@Test public void testLimitedInputStream() throws Exception {
  InputStream is=StreamUtilsTest.class.getResourceAsStream("/StreamUtilsTest.txt");
  assertThat(10,is(is.available()));
  is=StreamUtils.limitedInputStream(is,2);
  assertThat(2,is(is.available()));
  assertThat(is.markSupported(),is(true));
  is.mark(0);
  assertEquals((int)'0',is.read());
  assertEquals((int)'1',is.read());
  assertEquals(-1,is.read());
  is.reset();
  is.skip(1);
  assertEquals((int)'1',is.read());
  is.reset();
  is.skip(-1);
  assertEquals((int)'0',is.read());
  is.reset();
  byte[] bytes=new byte[2];
  int read=is.read(bytes,1,1);
  assertThat(read,is(1));
  is.reset();
  StreamUtils.skipUnusedStream(is);
  assertEquals(-1,is.read());
  is.close();
}
