@Test public void testSerialization() throws Exception {
  CharArrayBuffer buf=new CharArrayBuffer(32);
  buf.append("name: value");
  BufferedHeader orig=new BufferedHeader(buf);
  ByteArrayOutputStream outbuffer=new ByteArrayOutputStream();
  ObjectOutputStream outstream=new ObjectOutputStream(outbuffer);
  outstream.writeObject(orig);
  outstream.close();
  byte[] raw=outbuffer.toByteArray();
  ByteArrayInputStream inbuffer=new ByteArrayInputStream(raw);
  ObjectInputStream instream=new ObjectInputStream(inbuffer);
  BufferedHeader clone=(BufferedHeader)instream.readObject();
  Assert.assertEquals(orig.getName(),clone.getName());
  Assert.assertEquals(orig.getValue(),clone.getValue());
}
