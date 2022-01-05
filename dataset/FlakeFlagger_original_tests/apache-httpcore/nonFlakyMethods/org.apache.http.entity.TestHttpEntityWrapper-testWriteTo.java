@Test public void testWriteTo() throws Exception {
  String s="Message content";
  byte[] bytes=s.getBytes(HTTP.ISO_8859_1);
  StringEntity httpentity=new StringEntity(s);
  HttpEntityWrapper wrapped=new HttpEntityWrapper(httpentity);
  ByteArrayOutputStream out=new ByteArrayOutputStream();
  wrapped.writeTo(out);
  byte[] bytes2=out.toByteArray();
  Assert.assertNotNull(bytes2);
  Assert.assertEquals(bytes.length,bytes2.length);
  for (int i=0; i < bytes.length; i++) {
    Assert.assertEquals(bytes[i],bytes2[i]);
  }
  out=new ByteArrayOutputStream();
  wrapped.writeTo(out);
  bytes2=out.toByteArray();
  Assert.assertNotNull(bytes2);
  Assert.assertEquals(bytes.length,bytes2.length);
  for (int i=0; i < bytes.length; i++) {
    Assert.assertEquals(bytes[i],bytes2[i]);
  }
  try {
    wrapped.writeTo(null);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
}
