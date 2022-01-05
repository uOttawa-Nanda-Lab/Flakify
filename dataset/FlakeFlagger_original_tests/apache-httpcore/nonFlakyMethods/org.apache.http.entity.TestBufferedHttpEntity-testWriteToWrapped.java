@Test public void testWriteToWrapped() throws Exception {
  byte[] bytes="Message content".getBytes(HTTP.US_ASCII);
  ByteArrayEntity httpentity=new ByteArrayEntity(bytes);
  BufferedHttpEntity bufentity=new BufferedHttpEntity(httpentity);
  ByteArrayOutputStream out=new ByteArrayOutputStream();
  bufentity.writeTo(out);
  byte[] bytes2=out.toByteArray();
  Assert.assertNotNull(bytes2);
  Assert.assertEquals(bytes.length,bytes2.length);
  for (int i=0; i < bytes.length; i++) {
    Assert.assertEquals(bytes[i],bytes2[i]);
  }
  out=new ByteArrayOutputStream();
  bufentity.writeTo(out);
  bytes2=out.toByteArray();
  Assert.assertNotNull(bytes2);
  Assert.assertEquals(bytes.length,bytes2.length);
  for (int i=0; i < bytes.length; i++) {
    Assert.assertEquals(bytes[i],bytes2[i]);
  }
  try {
    bufentity.writeTo(null);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
}
