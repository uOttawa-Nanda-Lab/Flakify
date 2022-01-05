@Test public void testComplexReadWriteLine() throws Exception {
  SessionOutputBufferMock outbuffer=new SessionOutputBufferMock();
  outbuffer.write(new byte[]{'a','\n'});
  outbuffer.write(new byte[]{'\r','\n'});
  outbuffer.write(new byte[]{'\r','\r','\n'});
  outbuffer.write(new byte[]{'\n'});
  outbuffer.write(null);
  outbuffer.write(null,0,12);
  outbuffer.flush();
  long bytesWritten=outbuffer.getMetrics().getBytesTransferred();
  Assert.assertEquals(8,bytesWritten);
  StringBuilder buffer=new StringBuilder();
  for (int i=0; i < 14; i++) {
    buffer.append("a");
  }
  String s1=buffer.toString();
  buffer.append("\r\n");
  outbuffer.write(buffer.toString().getBytes("US-ASCII"));
  outbuffer.flush();
  bytesWritten=outbuffer.getMetrics().getBytesTransferred();
  Assert.assertEquals(8 + 14 + 2,bytesWritten);
  buffer.setLength(0);
  for (int i=0; i < 15; i++) {
    buffer.append("a");
  }
  String s2=buffer.toString();
  buffer.append("\r\n");
  outbuffer.write(buffer.toString().getBytes("US-ASCII"));
  outbuffer.flush();
  bytesWritten=outbuffer.getMetrics().getBytesTransferred();
  Assert.assertEquals(8 + 14 + 2+ 15+ 2,bytesWritten);
  buffer.setLength(0);
  for (int i=0; i < 16; i++) {
    buffer.append("a");
  }
  String s3=buffer.toString();
  buffer.append("\r\n");
  outbuffer.write(buffer.toString().getBytes("US-ASCII"));
  outbuffer.flush();
  bytesWritten=outbuffer.getMetrics().getBytesTransferred();
  Assert.assertEquals(8 + 14 + 2+ 15+ 2+ 16+ 2,bytesWritten);
  outbuffer.write(new byte[]{'a'});
  outbuffer.flush();
  bytesWritten=outbuffer.getMetrics().getBytesTransferred();
  Assert.assertEquals(8 + 14 + 2+ 15+ 2+ 16+ 2+ 1,bytesWritten);
  SessionInputBufferMock inbuffer=new SessionInputBufferMock(outbuffer.getData());
  Assert.assertEquals("a",inbuffer.readLine());
  Assert.assertEquals("",inbuffer.readLine());
  Assert.assertEquals("\r",inbuffer.readLine());
  Assert.assertEquals("",inbuffer.readLine());
  Assert.assertEquals(s1,inbuffer.readLine());
  Assert.assertEquals(s2,inbuffer.readLine());
  Assert.assertEquals(s3,inbuffer.readLine());
  Assert.assertEquals("a",inbuffer.readLine());
  Assert.assertNull(inbuffer.readLine());
  Assert.assertNull(inbuffer.readLine());
  long bytesRead=inbuffer.getMetrics().getBytesTransferred();
  Assert.assertEquals(bytesWritten,bytesRead);
}
