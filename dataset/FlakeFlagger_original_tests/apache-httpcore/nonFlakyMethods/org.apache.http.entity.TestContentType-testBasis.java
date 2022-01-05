@Test public void testBasis() throws Exception {
  ContentType contentType=ContentType.create("text/plain","ascii");
  Assert.assertEquals("text/plain",contentType.getMimeType());
  Assert.assertEquals("ascii",contentType.getCharset());
  Assert.assertEquals("text/plain; charset=ascii",contentType.toString());
}
