@Test public void testContentEncoding() throws Exception {
  BasicHttpEntity httpentity=new BasicHttpEntity();
  httpentity.setContentEncoding(new BasicHeader(HTTP.CONTENT_ENCODING,"gzip"));
  Assert.assertEquals(HTTP.CONTENT_ENCODING,httpentity.getContentEncoding().getName());
  Assert.assertEquals("gzip",httpentity.getContentEncoding().getValue());
  httpentity.setContentEncoding("gzip");
  Assert.assertEquals(HTTP.CONTENT_ENCODING,httpentity.getContentEncoding().getName());
  Assert.assertEquals("gzip",httpentity.getContentEncoding().getValue());
  httpentity.setContentEncoding((Header)null);
  Assert.assertNull(httpentity.getContentEncoding());
  httpentity.setContentEncoding((String)null);
  Assert.assertNull(httpentity.getContentEncoding());
}
