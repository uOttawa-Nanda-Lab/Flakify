@Test public void testBasics() throws Exception {
  byte[] bytes="Message content".getBytes(HTTP.US_ASCII);
  ByteArrayEntity httpentity=new ByteArrayEntity(bytes);
  Assert.assertEquals(bytes.length,httpentity.getContentLength());
  Assert.assertNotNull(httpentity.getContent());
  Assert.assertTrue(httpentity.isRepeatable());
  Assert.assertFalse(httpentity.isStreaming());
}
