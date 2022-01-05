@Test public void testWriteToOffLen() throws Exception {
  byte[] bytes="Message content".getBytes(HTTP.US_ASCII);
  int off=8;
  int len=7;
  ByteArrayEntity httpentity=new ByteArrayEntity(bytes,off,len);
  ByteArrayOutputStream out=new ByteArrayOutputStream();
  httpentity.writeTo(out);
  byte[] bytes2=out.toByteArray();
  Assert.assertNotNull(bytes2);
  Assert.assertEquals(len,bytes2.length);
  for (int i=0; i < len; i++) {
    Assert.assertEquals(bytes[i + off],bytes2[i]);
  }
  out=new ByteArrayOutputStream();
  httpentity.writeTo(out);
  bytes2=out.toByteArray();
  Assert.assertNotNull(bytes2);
  Assert.assertEquals(len,bytes2.length);
  for (int i=0; i < len; i++) {
    Assert.assertEquals(bytes[i + off],bytes2[i]);
  }
  try {
    httpentity.writeTo(null);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
}
