@Test public void testReadByteBufferWithMaxLen() throws Exception {
  byte[] pattern="0123456789ABCDEF".getBytes("US-ASCII");
  ReadableByteChannel channel=newChannel(pattern);
  HttpParams params=new BasicHttpParams();
  SessionInputBuffer inbuf=new SessionInputBufferImpl(4096,1024,params);
  while (inbuf.fill(channel) > 0) {
  }
  ByteBuffer dst=ByteBuffer.allocate(16);
  Assert.assertEquals(10,inbuf.read(dst,10));
  dst.flip();
  Assert.assertEquals(dst,ByteBuffer.wrap(pattern,0,10));
  dst.clear();
  Assert.assertEquals(3,inbuf.read(dst,3));
  dst.flip();
  Assert.assertEquals(dst,ByteBuffer.wrap(pattern,10,3));
  Assert.assertEquals(3,inbuf.read(dst,20));
  dst.flip();
  Assert.assertEquals(dst,ByteBuffer.wrap(pattern,13,3));
}
