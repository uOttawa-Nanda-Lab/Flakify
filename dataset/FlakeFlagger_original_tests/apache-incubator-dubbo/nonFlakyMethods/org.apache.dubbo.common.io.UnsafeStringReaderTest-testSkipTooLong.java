@Test public void testSkipTooLong() throws IOException {
  UnsafeStringReader reader=new UnsafeStringReader("abc");
  reader.skip(10);
  long skip=reader.skip(10);
  assertThat(skip,is(0L));
}
