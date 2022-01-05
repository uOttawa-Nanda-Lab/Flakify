@Test public void testSerialization() throws Exception {
  BasicStatusLine orig=new BasicStatusLine(HttpVersion.HTTP_1_1,HttpStatus.SC_OK,"OK");
  ByteArrayOutputStream outbuffer=new ByteArrayOutputStream();
  ObjectOutputStream outstream=new ObjectOutputStream(outbuffer);
  outstream.writeObject(orig);
  outstream.close();
  byte[] raw=outbuffer.toByteArray();
  ByteArrayInputStream inbuffer=new ByteArrayInputStream(raw);
  ObjectInputStream instream=new ObjectInputStream(inbuffer);
  BasicStatusLine clone=(BasicStatusLine)instream.readObject();
  Assert.assertEquals(orig.getReasonPhrase(),clone.getReasonPhrase());
  Assert.assertEquals(orig.getStatusCode(),clone.getStatusCode());
  Assert.assertEquals(orig.getProtocolVersion(),clone.getProtocolVersion());
}
