@Test public void testWriteTo() throws Exception {
  File tmpfile=File.createTempFile("testfile",".txt");
  tmpfile.deleteOnExit();
  FileOutputStream outstream=new FileOutputStream(tmpfile);
  outstream.write(0);
  outstream.write(1);
  outstream.write(2);
  outstream.write(3);
  outstream.close();
  FileEntity httpentity=new FileEntity(tmpfile,ContentType.create("text/plain",HTTP.ISO_8859_1));
  ByteArrayOutputStream out=new ByteArrayOutputStream();
  httpentity.writeTo(out);
  byte[] bytes=out.toByteArray();
  Assert.assertNotNull(bytes);
  Assert.assertEquals(tmpfile.length(),bytes.length);
  for (int i=0; i < 4; i++) {
    Assert.assertEquals(i,bytes[i]);
  }
  if (!tmpfile.delete()) {
    Assert.fail("Failed to delete: " + tmpfile);
  }
  try {
    httpentity.writeTo(null);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
}
