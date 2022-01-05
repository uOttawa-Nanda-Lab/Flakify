@Test public void testWriteByteBuffer() throws Exception {
  byte[] pattern="0123456789ABCDEF0123456789ABCDEF".getBytes("US-ASCII");
  HttpParams params=new BasicHttpParams();
  SessionOutputBuffer outbuf=new SessionOutputBufferImpl(4096,1024,params);
  ReadableByteChannel src=newChannel(pattern);
  outbuf.write(src);
  ByteArrayOutputStream outstream=new ByteArrayOutputStream();
  WritableByteChannel channel=newChannel(outstream);
  while (outbuf.flush(channel) > 0) {
  }
  Assert.assertEquals(ByteBuffer.wrap(pattern),ByteBuffer.wrap(outstream.toByteArray()));
}
