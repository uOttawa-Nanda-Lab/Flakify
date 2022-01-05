@Test public void testLowCaseText() throws Exception {
  ContentType contentType=ContentType.create("Text/Plain","ASCII");
  Assert.assertEquals("text/plain",contentType.getMimeType());
  Assert.assertEquals("ascii",contentType.getCharset());
}
