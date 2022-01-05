@Test public void testReadByteBuffer() throws Exception {
  byte[] pattern="0123456789ABCDEF".getBytes("US-ASCII");
  ReadableByteChannel channel=newChannel(pattern);
  HttpParams params=new BasicHttpParams();
  SessionInputBuffer inbuf=new SessionInputBufferImpl(4096,1024,params);
  while (inbuf.fill(channel) > 0) {
  }
  ByteBuffer dst=ByteBuffer.allocate(10);
  Assert.assertEquals(10,inbuf.read(dst));
  dst.flip();
  Assert.assertEquals(dst,ByteBuffer.wrap(pattern,0,10));
  dst.clear();
  Assert.assertEquals(6,inbuf.read(dst));
  dst.flip();
  Assert.assertEquals(dst,ByteBuffer.wrap(pattern,10,6));
}
