@Test public void testParse() throws Exception {
  ContentType contentType=ContentType.parse("text/plain; charset=\"ascii\"");
  Assert.assertEquals("text/plain",contentType.getMimeType());
  Assert.assertEquals("ascii",contentType.getCharset());
  Assert.assertEquals("text/plain; charset=ascii",contentType.toString());
}
