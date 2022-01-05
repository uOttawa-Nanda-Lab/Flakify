@Test public void testSkip() throws IOException {
  UnsafeStringReader reader=new UnsafeStringReader("abc");
  assertThat(reader.ready(),is(true));
  reader.skip(1);
  assertThat(reader.read(),is((int)'b'));
}
