@Test public void testMaxIntContentToByteArray() throws Exception {
  byte[] content="Message content".getBytes("ISO-8859-1");
  BasicHttpEntity httpentity=new BasicHttpEntity();
  httpentity.setContent(new ByteArrayInputStream(content));
  httpentity.setContentLength(Integer.MAX_VALUE + 100L);
  try {
    EntityUtils.toByteArray(httpentity);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
}
