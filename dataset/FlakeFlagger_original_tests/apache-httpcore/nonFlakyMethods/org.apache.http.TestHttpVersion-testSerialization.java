@Test public void testSerialization() throws Exception {
  HttpVersion orig=HttpVersion.HTTP_1_1;
  ByteArrayOutputStream outbuffer=new ByteArrayOutputStream();
  ObjectOutputStream outstream=new ObjectOutputStream(outbuffer);
  outstream.writeObject(orig);
  outstream.close();
  byte[] raw=outbuffer.toByteArray();
  ByteArrayInputStream inbuffer=new ByteArrayInputStream(raw);
  ObjectInputStream instream=new ObjectInputStream(inbuffer);
  HttpVersion clone=(HttpVersion)instream.readObject();
  Assert.assertEquals(orig,clone);
}
