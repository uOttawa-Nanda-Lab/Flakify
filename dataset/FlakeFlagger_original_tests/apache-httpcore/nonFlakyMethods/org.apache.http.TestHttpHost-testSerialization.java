@Test public void testSerialization() throws Exception {
  HttpHost orig=new HttpHost("somehost",8080,"https");
  ByteArrayOutputStream outbuffer=new ByteArrayOutputStream();
  ObjectOutputStream outstream=new ObjectOutputStream(outbuffer);
  outstream.writeObject(orig);
  outstream.close();
  byte[] raw=outbuffer.toByteArray();
  ByteArrayInputStream inbuffer=new ByteArrayInputStream(raw);
  ObjectInputStream instream=new ObjectInputStream(inbuffer);
  HttpHost clone=(HttpHost)instream.readObject();
  Assert.assertEquals(orig,clone);
}
