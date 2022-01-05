@Test public void testReadOneByte() throws Exception {
  byte[] out=new byte[40];
  for (int i=0; i < out.length; i++) {
    out[i]=(byte)('0' + i);
  }
  ReadableByteChannel channel=newChannel(out);
  HttpParams params=new BasicHttpParams();
  SessionInputBuffer inbuf=new SessionInputBufferImpl(16,16,params);
  while (inbuf.fill(channel) > 0) {
  }
  byte[] in=new byte[40];
  for (int i=0; i < in.length; i++) {
    in[i]=(byte)inbuf.read();
  }
  for (int i=0; i < out.length; i++) {
    Assert.assertEquals(out[i],in[i]);
  }
}
