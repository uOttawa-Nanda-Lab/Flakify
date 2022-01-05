@Test public void testCharsetAndQuoting() throws Exception {
  MediaType mediaType=MediaType.parse("text/plain;a=\";charset=us-ascii\";charset=\"utf-8\";b=\"iso-8859-1\"");
  assertEquals("UTF-8",mediaType.charset().name());
}
