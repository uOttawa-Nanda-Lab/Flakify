@Test public void testWrite4() throws Exception {
  assertThat((int)IOUtils.write(is,os),equalTo(TEXT.length()));
}
