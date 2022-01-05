@Test public void testWrite1() throws Exception {
  assertThat((int)IOUtils.write(is,os,16),equalTo(TEXT.length()));
}
