@Test public void testBufferingEntity() throws Exception {
  byte[] bytes="Message content".getBytes(HTTP.US_ASCII);
  InputStreamEntity httpentity=new InputStreamEntity(new ByteArrayInputStream(bytes),-1);
  BufferedHttpEntity bufentity=new BufferedHttpEntity(httpentity);
  Assert.assertEquals(bytes.length,bufentity.getContentLength());
  Assert.assertTrue(bufentity.isRepeatable());
  Assert.assertFalse(bufentity.isChunked());
  Assert.assertFalse(bufentity.isStreaming());
  Assert.assertNotNull(bufentity.getContent());
  Assert.assertNotNull(bufentity.getContent());
}
