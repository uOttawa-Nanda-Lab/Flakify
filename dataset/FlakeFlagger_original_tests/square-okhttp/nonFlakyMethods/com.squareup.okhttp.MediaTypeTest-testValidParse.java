@Test public void testValidParse() throws Exception {
  assertMediaType("text/plain");
  assertMediaType("application/atom+xml; charset=utf-8");
  assertMediaType("application/atom+xml; a=1; a=2; b=3");
  assertMediaType("image/gif; foo=bar");
  assertMediaType("text/plain; a=1");
  assertMediaType("text/plain; a=1; a=2; b=3");
  assertMediaType("text/plain; charset=utf-16");
  assertMediaType("text/plain; \t \n \r a=b");
}
