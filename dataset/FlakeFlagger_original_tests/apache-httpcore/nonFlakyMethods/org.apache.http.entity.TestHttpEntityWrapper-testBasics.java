@Test public void testBasics() throws Exception {
  String s="Message content";
  StringEntity httpentity=new StringEntity(s,HTTP.ISO_8859_1);
  httpentity.setContentType(HTTP.PLAIN_TEXT_TYPE);
  httpentity.setContentEncoding(HTTP.IDENTITY_CODING);
  HttpEntityWrapper wrapped=new HttpEntityWrapper(httpentity);
  Assert.assertEquals(httpentity.getContentLength(),wrapped.getContentLength());
  Assert.assertEquals(httpentity.getContentType(),wrapped.getContentType());
  Assert.assertEquals(httpentity.getContentEncoding(),wrapped.getContentEncoding());
  Assert.assertEquals(httpentity.isChunked(),wrapped.isChunked());
  Assert.assertEquals(httpentity.isRepeatable(),wrapped.isRepeatable());
  Assert.assertEquals(httpentity.isStreaming(),wrapped.isStreaming());
  Assert.assertNotNull(wrapped.getContent());
}
