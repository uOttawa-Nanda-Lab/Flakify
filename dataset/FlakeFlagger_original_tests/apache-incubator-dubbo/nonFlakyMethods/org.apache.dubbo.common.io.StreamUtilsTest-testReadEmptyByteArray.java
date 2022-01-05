@Test(expected=NullPointerException.class) public void testReadEmptyByteArray() throws IOException {
  InputStream is=StreamUtilsTest.class.getResourceAsStream("/StreamUtilsTest.txt");
  try {
    is=StreamUtils.limitedInputStream(is,2);
    is.read(null,0,1);
  }
  finally {
    if (is != null) {
      is.close();
    }
  }
}
