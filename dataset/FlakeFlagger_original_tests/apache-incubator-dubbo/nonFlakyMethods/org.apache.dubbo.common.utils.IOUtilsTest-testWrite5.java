@Test public void testWrite5() throws Exception {
  assertThat((int)IOUtils.write(reader,writer),equalTo(TEXT.length()));
}
