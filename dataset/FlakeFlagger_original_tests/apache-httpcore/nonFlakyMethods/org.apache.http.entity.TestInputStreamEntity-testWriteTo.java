@Test public void testWriteTo() throws Exception {
  byte[] bytes="Message content".getBytes(HTTP.ISO_8859_1);
  InputStream instream=new ByteArrayInputStream(bytes);
  InputStreamEntity httpentity=new InputStreamEntity(instream,7);
  ByteArrayOutputStream out=new ByteArrayOutputStream();
  httpentity.writeTo(out);
  byte[] bytes2=out.toByteArray();
  Assert.assertNotNull(bytes2);
  Assert.assertEquals(7,bytes2.length);
  String s=new String(bytes2,HTTP.ISO_8859_1);
  Assert.assertEquals("Message",s);
  instream=new ByteArrayInputStream(bytes);
  httpentity=new InputStreamEntity(instream,20);
  out=new ByteArrayOutputStream();
  httpentity.writeTo(out);
  bytes2=out.toByteArray();
  Assert.assertNotNull(bytes2);
  Assert.assertEquals(bytes.length,bytes2.length);
  instream=new ByteArrayInputStream(bytes);
  httpentity=new InputStreamEntity(instream,-1);
  out=new ByteArrayOutputStream();
  httpentity.writeTo(out);
  bytes2=out.toByteArray();
  Assert.assertNotNull(bytes2);
  Assert.assertEquals(bytes.length,bytes2.length);
  try {
    httpentity.writeTo(null);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
}
