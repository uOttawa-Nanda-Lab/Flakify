@Test public void testBasics() throws Exception {
  String s="Message content";
  StringEntity httpentity=new StringEntity(s,HTTP.ISO_8859_1);
  byte[] bytes=s.getBytes(HTTP.ISO_8859_1);
  Assert.assertEquals(bytes.length,httpentity.getContentLength());
  Assert.assertNotNull(httpentity.getContent());
  Assert.assertTrue(httpentity.isRepeatable());
  Assert.assertFalse(httpentity.isStreaming());
}
