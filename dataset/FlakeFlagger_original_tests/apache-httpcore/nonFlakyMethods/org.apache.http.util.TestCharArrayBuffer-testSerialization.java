@Test public void testSerialization() throws Exception {
  CharArrayBuffer orig=new CharArrayBuffer(32);
  orig.append('a');
  orig.append('b');
  orig.append('c');
  ByteArrayOutputStream outbuffer=new ByteArrayOutputStream();
  ObjectOutputStream outstream=new ObjectOutputStream(outbuffer);
  outstream.writeObject(orig);
  outstream.close();
  byte[] raw=outbuffer.toByteArray();
  ByteArrayInputStream inbuffer=new ByteArrayInputStream(raw);
  ObjectInputStream instream=new ObjectInputStream(inbuffer);
  CharArrayBuffer clone=(CharArrayBuffer)instream.readObject();
  Assert.assertEquals(orig.capacity(),clone.capacity());
  Assert.assertEquals(orig.length(),clone.length());
  char[] data=clone.toCharArray();
  Assert.assertNotNull(data);
  Assert.assertEquals(3,data.length);
  Assert.assertEquals('a',data[0]);
  Assert.assertEquals('b',data[1]);
  Assert.assertEquals('c',data[2]);
}
