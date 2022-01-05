@Test public void testContentType() throws Exception {
  BasicHttpEntity httpentity=new BasicHttpEntity();
  httpentity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE,HTTP.PLAIN_TEXT_TYPE));
  Assert.assertEquals(HTTP.CONTENT_TYPE,httpentity.getContentType().getName());
  Assert.assertEquals(HTTP.PLAIN_TEXT_TYPE,httpentity.getContentType().getValue());
  httpentity.setContentType(HTTP.PLAIN_TEXT_TYPE);
  Assert.assertEquals(HTTP.CONTENT_TYPE,httpentity.getContentType().getName());
  Assert.assertEquals(HTTP.PLAIN_TEXT_TYPE,httpentity.getContentType().getValue());
  httpentity.setContentType((Header)null);
  Assert.assertNull(httpentity.getContentType());
  httpentity.setContentType((String)null);
  Assert.assertNull(httpentity.getContentType());
}
