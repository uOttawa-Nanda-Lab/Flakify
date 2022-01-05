@Test public void testWriteTo() throws Exception {
  HttpEntity httpentity=new EntityTemplate(new ContentProducer(){
    public void writeTo(    final OutputStream outstream) throws IOException {
      outstream.write('a');
    }
  }
);
  ByteArrayOutputStream out=new ByteArrayOutputStream();
  httpentity.writeTo(out);
  byte[] bytes2=out.toByteArray();
  Assert.assertNotNull(bytes2);
  Assert.assertEquals(1,bytes2.length);
  try {
    httpentity.writeTo(null);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
}
