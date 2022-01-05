@Test public void testBasicOffLen() throws Exception {
  byte[] bytes="Message content".getBytes(HTTP.US_ASCII);
  ByteArrayEntity httpentity=new ByteArrayEntity(bytes,8,7);
  Assert.assertEquals(7,httpentity.getContentLength());
  Assert.assertNotNull(httpentity.getContent());
  Assert.assertTrue(httpentity.isRepeatable());
  Assert.assertFalse(httpentity.isStreaming());
}
