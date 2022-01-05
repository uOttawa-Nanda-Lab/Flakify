@Test public void testWrite2() throws Exception {
  assertThat((int)IOUtils.write(reader,writer,16),equalTo(TEXT.length()));
}
