@Test public void testExtractNoCharset() throws Exception {
  BasicHttpEntity httpentity=new BasicHttpEntity();
  httpentity.setContentType(new BasicHeader("Content-Type","text/plain; param=yadayada"));
  ContentType contentType=ContentType.get(httpentity);
  Assert.assertNotNull(contentType);
  Assert.assertEquals("text/plain",contentType.getMimeType());
  Assert.assertNull(contentType.getCharset());
}
