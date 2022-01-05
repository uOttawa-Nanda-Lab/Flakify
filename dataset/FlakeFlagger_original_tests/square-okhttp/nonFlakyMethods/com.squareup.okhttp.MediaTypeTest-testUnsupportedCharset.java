@Test public void testUnsupportedCharset(){
  MediaType mediaType=MediaType.parse("text/plain; charset=utf-wtf");
  try {
    mediaType.charset();
    fail();
  }
 catch (  UnsupportedCharsetException expected) {
  }
}
