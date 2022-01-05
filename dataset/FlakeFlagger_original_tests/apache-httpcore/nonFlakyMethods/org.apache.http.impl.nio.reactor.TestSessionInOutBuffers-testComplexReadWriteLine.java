@Test public void testComplexReadWriteLine() throws Exception {
  HttpParams params=new BasicHttpParams();
  SessionOutputBuffer outbuf=new SessionOutputBufferImpl(1024,16,params);
  outbuf.write(ByteBuffer.wrap(new byte[]{'a','\n'}));
  outbuf.write(ByteBuffer.wrap(new byte[]{'\r','\n'}));
  outbuf.write(ByteBuffer.wrap(new byte[]{'\r','\r','\n'}));
  outbuf.write(ByteBuffer.wrap(new byte[]{'\n'}));
  StringBuilder buffer=new StringBuilder();
  for (int i=0; i < 14; i++) {
    buffer.append("a");
  }
  String s1=buffer.toString();
  buffer.append("\r\n");
  outbuf.write(ByteBuffer.wrap(buffer.toString().getBytes("US-ASCII")));
  buffer.setLength(0);
  for (int i=0; i < 15; i++) {
    buffer.append("a");
  }
  String s2=buffer.toString();
  buffer.append("\r\n");
  outbuf.write(ByteBuffer.wrap(buffer.toString().getBytes("US-ASCII")));
  buffer.setLength(0);
  for (int i=0; i < 16; i++) {
    buffer.append("a");
  }
  String s3=buffer.toString();
  buffer.append("\r\n");
  outbuf.write(ByteBuffer.wrap(buffer.toString().getBytes("US-ASCII")));
  outbuf.write(ByteBuffer.wrap(new byte[]{'a'}));
  ByteArrayOutputStream outstream=new ByteArrayOutputStream();
  WritableByteChannel outChannel=newChannel(outstream);
  outbuf.flush(outChannel);
  ReadableByteChannel channel=newChannel(outstream.toByteArray());
  SessionInputBuffer inbuf=new SessionInputBufferImpl(1024,16,params);
  inbuf.fill(channel);
  Assert.assertEquals("a",inbuf.readLine(true));
  Assert.assertEquals("",inbuf.readLine(true));
  Assert.assertEquals("\r",inbuf.readLine(true));
  Assert.assertEquals("",inbuf.readLine(true));
  Assert.assertEquals(s1,inbuf.readLine(true));
  Assert.assertEquals(s2,inbuf.readLine(true));
  Assert.assertEquals(s3,inbuf.readLine(true));
  Assert.assertEquals("a",inbuf.readLine(true));
  Assert.assertNull(inbuf.readLine(true));
  Assert.assertNull(inbuf.readLine(true));
}
