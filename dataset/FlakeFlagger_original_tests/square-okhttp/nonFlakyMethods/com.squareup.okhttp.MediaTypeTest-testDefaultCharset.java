@Test public void testDefaultCharset() throws Exception {
  MediaType noCharset=MediaType.parse("text/plain");
  assertEquals("UTF-8",noCharset.charset(Util.UTF_8).name());
  assertEquals("US-ASCII",noCharset.charset(Charset.forName("US-ASCII")).name());
  MediaType charset=MediaType.parse("text/plain; charset=iso-8859-1");
  assertEquals("ISO-8859-1",charset.charset(Util.UTF_8).name());
  assertEquals("ISO-8859-1",charset.charset(Charset.forName("US-ASCII")).name());
}
