@Test public void testReadWriteBytes() throws Exception {
  byte[] out=new byte[40];
  for (int i=0; i < out.length; i++) {
    out[i]=(byte)('0' + i);
  }
  SessionOutputBufferMock outbuffer=new SessionOutputBufferMock();
  int off=0;
  int remaining=out.length;
  while (remaining > 0) {
    int chunk=10;
    if (chunk > remaining) {
      chunk=remaining;
    }
    outbuffer.write(out,off,chunk);
    off+=chunk;
    remaining-=chunk;
  }
  outbuffer.flush();
  long bytesWritten=outbuffer.getMetrics().getBytesTransferred();
  Assert.assertEquals(out.length,bytesWritten);
  byte[] tmp=outbuffer.getData();
  Assert.assertEquals(out.length,tmp.length);
  for (int i=0; i < out.length; i++) {
    Assert.assertEquals(out[i],tmp[i]);
  }
  SessionInputBufferMock inbuffer=new SessionInputBufferMock(tmp);
  Assert.assertEquals(0,inbuffer.read(null,0,10));
  Assert.assertEquals(0,inbuffer.read(null));
  long bytesRead=inbuffer.getMetrics().getBytesTransferred();
  Assert.assertEquals(0,bytesRead);
  byte[] in=new byte[40];
  off=0;
  remaining=in.length;
  while (remaining > 0) {
    int chunk=10;
    if (chunk > remaining) {
      chunk=remaining;
    }
    int l=inbuffer.read(in,off,chunk);
    if (l == -1) {
      break;
    }
    off+=l;
    remaining-=l;
  }
  for (int i=0; i < out.length; i++) {
    Assert.assertEquals(out[i],in[i]);
  }
  Assert.assertEquals(-1,inbuffer.read(tmp));
  Assert.assertEquals(-1,inbuffer.read(tmp));
  bytesRead=inbuffer.getMetrics().getBytesTransferred();
  Assert.assertEquals(out.length,bytesRead);
}
