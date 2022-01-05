@Test public void testSerialization() throws Exception {
  BasicRequestLine orig=new BasicRequestLine("GET","/stuff",HttpVersion.HTTP_1_1);
  ByteArrayOutputStream outbuffer=new ByteArrayOutputStream();
  ObjectOutputStream outstream=new ObjectOutputStream(outbuffer);
  outstream.writeObject(orig);
  outstream.close();
  byte[] raw=outbuffer.toByteArray();
  ByteArrayInputStream inbuffer=new ByteArrayInputStream(raw);
  ObjectInputStream instream=new ObjectInputStream(inbuffer);
  BasicRequestLine clone=(BasicRequestLine)instream.readObject();
  Assert.assertEquals(orig.getMethod(),clone.getMethod());
  Assert.assertEquals(orig.getUri(),clone.getUri());
  Assert.assertEquals(orig.getProtocolVersion(),clone.getProtocolVersion());
}
