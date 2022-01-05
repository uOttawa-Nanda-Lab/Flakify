@Test public void testParse() throws Exception {
  MediaType mediaType=MediaType.parse("text/plain;boundary=foo;charset=utf-8");
  assertEquals("text",mediaType.type());
  assertEquals("plain",mediaType.subtype());
  assertEquals("UTF-8",mediaType.charset().name());
  assertEquals("text/plain;boundary=foo;charset=utf-8",mediaType.toString());
  assertTrue(mediaType.equals(MediaType.parse("text/plain;boundary=foo;charset=utf-8")));
  assertEquals(mediaType.hashCode(),MediaType.parse("text/plain;boundary=foo;charset=utf-8").hashCode());
}
