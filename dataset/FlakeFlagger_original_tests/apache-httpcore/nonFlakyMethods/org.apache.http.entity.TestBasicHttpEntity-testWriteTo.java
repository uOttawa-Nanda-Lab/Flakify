@Test public void testWriteTo() throws Exception {
  byte[] bytes="Message content".getBytes(HTTP.US_ASCII);
  InputStream content=new ByteArrayInputStream(bytes);
  BasicHttpEntity httpentity=new BasicHttpEntity();
  httpentity.setContent(content);
  ByteArrayOutputStream out=new ByteArrayOutputStream();
  httpentity.writeTo(out);
  byte[] bytes2=out.toByteArray();
  Assert.assertNotNull(bytes2);
  Assert.assertEquals(bytes.length,bytes2.length);
  for (int i=0; i < bytes.length; i++) {
    Assert.assertEquals(bytes[i],bytes2[i]);
  }
  httpentity.setContent(null);
  out=new ByteArrayOutputStream();
  try {
    httpentity.writeTo(out);
    Assert.fail("IllegalStateException should have been thrown");
  }
 catch (  IllegalStateException ex) {
  }
  try {
    httpentity.writeTo(null);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
}
