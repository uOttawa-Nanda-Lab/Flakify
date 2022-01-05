@Test public void testContent() throws Exception {
  byte[] bytes="Message content".getBytes(HTTP.US_ASCII);
  InputStream content=new ByteArrayInputStream(bytes);
  BasicHttpEntity httpentity=new BasicHttpEntity();
  try {
    httpentity.getContent();
    Assert.fail("IllegalStateException should have been thrown");
  }
 catch (  IllegalStateException ex) {
  }
  httpentity.setContent(content);
  Assert.assertEquals(content,httpentity.getContent());
  httpentity.setContent(null);
  try {
    httpentity.getContent();
    Assert.fail("IllegalStateException should have been thrown");
  }
 catch (  IllegalStateException ex) {
  }
}
