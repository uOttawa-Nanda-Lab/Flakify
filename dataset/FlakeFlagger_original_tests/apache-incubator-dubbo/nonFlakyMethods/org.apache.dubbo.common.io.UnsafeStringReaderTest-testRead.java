@Test public void testRead() throws IOException {
  UnsafeStringReader reader=new UnsafeStringReader("abc");
  assertThat(reader.markSupported(),is(true));
  assertThat(reader.read(),is((int)'a'));
  assertThat(reader.read(),is((int)'b'));
  assertThat(reader.read(),is((int)'c'));
  assertThat(reader.read(),is(-1));
  reader.reset();
  reader.mark(0);
  assertThat(reader.read(),is((int)'a'));
  char[] chars=new char[2];
  reader.read(chars);
  reader.close();
  assertThat(chars[0],is('b'));
  assertThat(chars[1],is('c'));
}
