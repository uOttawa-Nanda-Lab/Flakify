@Test public void testParseWithSpecialCharacters() throws Exception {
  MediaType mediaType=MediaType.parse("!#$%&'*+-.{|}~/!#$%&'*+-.{|}~; !#$%&'*+-.{|}~=!#$%&'*+-.{|}~");
  assertEquals("!#$%&'*+-.{|}~",mediaType.type());
  assertEquals("!#$%&'*+-.{|}~",mediaType.subtype());
}
