@Test public void testUnknownLengthContentToByteArray() throws Exception {
  byte[] bytes="Message content".getBytes("ISO-8859-1");
  BasicHttpEntity httpentity=new BasicHttpEntity();
  httpentity.setContent(new ByteArrayInputStream(bytes));
  httpentity.setContentLength(-1L);
  byte[] bytes2=EntityUtils.toByteArray(httpentity);
  Assert.assertNotNull(bytes2);
  Assert.assertEquals(bytes.length,bytes2.length);
  for (int i=0; i < bytes.length; i++) {
    Assert.assertEquals(bytes[i],bytes2[i]);
  }
}
