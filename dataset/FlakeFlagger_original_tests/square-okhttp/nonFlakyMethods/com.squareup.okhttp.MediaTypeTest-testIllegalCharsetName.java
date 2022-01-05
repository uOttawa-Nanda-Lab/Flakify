@Test public void testIllegalCharsetName(){
  MediaType mediaType=MediaType.parse("text/plain; charset=\"!@#$%^&*()\"");
  try {
    mediaType.charset();
    fail();
  }
 catch (  IllegalCharsetNameException expected) {
  }
}
