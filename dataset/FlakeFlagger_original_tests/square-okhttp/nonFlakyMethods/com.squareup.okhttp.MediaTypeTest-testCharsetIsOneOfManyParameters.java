@Test public void testCharsetIsOneOfManyParameters() throws Exception {
  MediaType mediaType=MediaType.parse("text/plain;a=1;b=2;charset=utf-8;c=3");
  assertEquals("text",mediaType.type());
  assertEquals("plain",mediaType.subtype());
  assertEquals("UTF-8",mediaType.charset().name());
}
