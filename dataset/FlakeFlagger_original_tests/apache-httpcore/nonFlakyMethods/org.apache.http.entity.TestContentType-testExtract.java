@Test public void testExtract() throws Exception {
  BasicHttpEntity httpentity=new BasicHttpEntity();
  httpentity.setContentType(new BasicHeader("Content-Type","text/plain; charset = UTF-8"));
  ContentType contentType=ContentType.get(httpentity);
  Assert.assertNotNull(contentType);
  Assert.assertEquals("text/plain",contentType.getMimeType());
  Assert.assertEquals("utf-8",contentType.getCharset());
}
