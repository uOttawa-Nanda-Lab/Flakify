@Test public void testWrappingEntity() throws Exception {
  byte[] bytes="Message content".getBytes(HTTP.US_ASCII);
  ByteArrayEntity httpentity=new ByteArrayEntity(bytes);
  httpentity.setChunked(true);
  BufferedHttpEntity bufentity=new BufferedHttpEntity(httpentity);
  Assert.assertEquals(bytes.length,bufentity.getContentLength());
  Assert.assertTrue(bufentity.isRepeatable());
  Assert.assertTrue(bufentity.isChunked());
  Assert.assertFalse(bufentity.isStreaming());
  Assert.assertNotNull(bufentity.getContent());
  Assert.assertNotNull(bufentity.getContent());
}
