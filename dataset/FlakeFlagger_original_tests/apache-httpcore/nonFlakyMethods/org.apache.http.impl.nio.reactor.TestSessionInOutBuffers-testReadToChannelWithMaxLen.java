@Test public void testReadToChannelWithMaxLen() throws Exception {
  byte[] pattern="0123456789ABCDEF".getBytes("US-ASCII");
  ReadableByteChannel channel=newChannel(pattern);
  HttpParams params=new BasicHttpParams();
  SessionInputBuffer inbuf=new SessionInputBufferImpl(4096,1024,params);
  while (inbuf.fill(channel) > 0) {
  }
  ByteArrayOutputStream outstream=new ByteArrayOutputStream();
  WritableByteChannel dst=newChannel(outstream);
  Assert.assertEquals(10,inbuf.read(dst,10));
  Assert.assertEquals(3,inbuf.read(dst,3));
  Assert.assertEquals(3,inbuf.read(dst,10));
  Assert.assertEquals(ByteBuffer.wrap(pattern),ByteBuffer.wrap(outstream.toByteArray()));
}
