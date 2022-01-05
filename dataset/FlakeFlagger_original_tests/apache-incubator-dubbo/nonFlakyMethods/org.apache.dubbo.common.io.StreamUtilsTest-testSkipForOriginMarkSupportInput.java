@Test public void testSkipForOriginMarkSupportInput() throws IOException {
  InputStream is=StreamUtilsTest.class.getResourceAsStream("/StreamUtilsTest.txt");
  InputStream newIs=StreamUtils.markSupportedInputStream(is,1);
  assertThat(newIs,is(is));
  is.close();
}
