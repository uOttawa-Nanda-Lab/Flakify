@Test public void testReadWriteByte() throws Exception {
  byte[] out=new byte[40];
  for (int i=0; i < out.length; i++) {
    out[i]=(byte)(120 + i);
  }
  SessionOutputBufferMock outbuffer=new SessionOutputBufferMock();
  for (int i=0; i < out.length; i++) {
    outbuffer.write(out[i]);
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
  byte[] in=new byte[40];
  for (int i=0; i < in.length; i++) {
    in[i]=(byte)inbuffer.read();
  }
  for (int i=0; i < out.length; i++) {
    Assert.assertEquals(out[i],in[i]);
  }
  Assert.assertEquals(-1,inbuffer.read());
  Assert.assertEquals(-1,inbuffer.read());
  long bytesRead=inbuffer.getMetrics().getBytesTransferred();
  Assert.assertEquals(out.length,bytesRead);
}
