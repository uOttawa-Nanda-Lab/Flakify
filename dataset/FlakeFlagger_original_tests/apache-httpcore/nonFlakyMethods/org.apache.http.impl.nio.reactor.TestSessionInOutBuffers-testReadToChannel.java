@Test public void testReadToChannel() throws Exception {
  byte[] pattern="0123456789ABCDEF".getBytes("US-ASCII");
  ReadableByteChannel channel=newChannel(pattern);
  HttpParams params=new BasicHttpParams();
  SessionInputBuffer inbuf=new SessionInputBufferImpl(4096,1024,params);
  while (inbuf.fill(channel) > 0) {
  }
  ByteArrayOutputStream outstream=new ByteArrayOutputStream();
  WritableByteChannel dst=newChannel(outstream);
  Assert.assertEquals(16,inbuf.read(dst));
  Assert.assertEquals(ByteBuffer.wrap(pattern),ByteBuffer.wrap(outstream.toByteArray()));
}
