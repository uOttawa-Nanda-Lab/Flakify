@Test public void testBasics() throws Exception {
  byte[] bytes="Message content".getBytes(HTTP.ISO_8859_1);
  InputStream instream=new ByteArrayInputStream(bytes);
  InputStreamEntity httpentity=new InputStreamEntity(instream,bytes.length);
  Assert.assertEquals(bytes.length,httpentity.getContentLength());
  Assert.assertEquals(instream,httpentity.getContent());
  Assert.assertNotNull(httpentity.getContent());
  Assert.assertFalse(httpentity.isRepeatable());
  Assert.assertTrue(httpentity.isStreaming());
}
