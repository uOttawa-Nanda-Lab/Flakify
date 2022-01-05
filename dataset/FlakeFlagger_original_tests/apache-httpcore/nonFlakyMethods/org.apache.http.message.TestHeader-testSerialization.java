@Test public void testSerialization() throws Exception {
  BasicHeader orig=new BasicHeader("name1","value1");
  ByteArrayOutputStream outbuffer=new ByteArrayOutputStream();
  ObjectOutputStream outstream=new ObjectOutputStream(outbuffer);
  outstream.writeObject(orig);
  outstream.close();
  byte[] raw=outbuffer.toByteArray();
  ByteArrayInputStream inbuffer=new ByteArrayInputStream(raw);
  ObjectInputStream instream=new ObjectInputStream(inbuffer);
  BasicHeader clone=(BasicHeader)instream.readObject();
  Assert.assertEquals(orig.getName(),clone.getName());
  Assert.assertEquals(orig.getValue(),clone.getValue());
}
