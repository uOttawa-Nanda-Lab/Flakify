@Test public void testRead() throws IOException {
  UnsafeByteArrayInputStream stream=new UnsafeByteArrayInputStream("abc".getBytes());
  assertThat(stream.read(),is((int)'a'));
  assertThat(stream.available(),is(2));
  stream.skip(1);
  assertThat(stream.available(),is(1));
  byte[] bytes=new byte[1];
  int read=stream.read(bytes);
  assertThat(read,is(1));
  assertThat(bytes,is("c".getBytes()));
  stream.reset();
  assertThat(stream.position(),is(0));
  assertThat(stream.size(),is(3));
  stream.position(1);
  assertThat(stream.read(),is((int)'b'));
}
