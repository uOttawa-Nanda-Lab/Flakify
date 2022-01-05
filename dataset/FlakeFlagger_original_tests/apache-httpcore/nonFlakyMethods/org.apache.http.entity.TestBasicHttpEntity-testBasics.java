@Test public void testBasics() throws Exception {
  byte[] bytes="Message content".getBytes(HTTP.US_ASCII);
  InputStream content=new ByteArrayInputStream(bytes);
  BasicHttpEntity httpentity=new BasicHttpEntity();
  httpentity.setContent(content);
  httpentity.setContentLength(bytes.length);
  Assert.assertEquals(bytes.length,httpentity.getContentLength());
  Assert.assertFalse(httpentity.isRepeatable());
  Assert.assertTrue(httpentity.isStreaming());
}
