@Test(expected=IOException.class) public void testMarkInputSupport() throws IOException {
  InputStream is=StreamUtilsTest.class.getResourceAsStream("/StreamUtilsTest.txt");
  try {
    is=StreamUtils.markSupportedInputStream(new PushbackInputStream(is),1);
    is.mark(1);
    int read=is.read();
    assertThat(read,is((int)'0'));
    is.skip(1);
    is.read();
  }
  finally {
    if (is != null) {
      is.close();
    }
  }
}
