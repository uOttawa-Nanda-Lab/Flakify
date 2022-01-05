@Test(expected=IndexOutOfBoundsException.class) public void testReadWithWrongOffset() throws IOException {
  InputStream is=StreamUtilsTest.class.getResourceAsStream("/StreamUtilsTest.txt");
  try {
    is=StreamUtils.limitedInputStream(is,2);
    is.read(new byte[1],-1,1);
  }
  finally {
    if (is != null) {
      is.close();
    }
  }
}
