@Test public void testSerialization() throws Exception {
  ByteArrayBuffer orig=new ByteArrayBuffer(32);
  orig.append(1);
  orig.append(2);
  orig.append(3);
  ByteArrayOutputStream outbuffer=new ByteArrayOutputStream();
  ObjectOutputStream outstream=new ObjectOutputStream(outbuffer);
  outstream.writeObject(orig);
  outstream.close();
  byte[] raw=outbuffer.toByteArray();
  ByteArrayInputStream inbuffer=new ByteArrayInputStream(raw);
  ObjectInputStream instream=new ObjectInputStream(inbuffer);
  ByteArrayBuffer clone=(ByteArrayBuffer)instream.readObject();
  Assert.assertEquals(orig.capacity(),clone.capacity());
  Assert.assertEquals(orig.length(),clone.length());
  byte[] data=clone.toByteArray();
  Assert.assertNotNull(data);
  Assert.assertEquals(3,data.length);
  Assert.assertEquals(1,data[0]);
  Assert.assertEquals(2,data[1]);
  Assert.assertEquals(3,data[2]);
}
