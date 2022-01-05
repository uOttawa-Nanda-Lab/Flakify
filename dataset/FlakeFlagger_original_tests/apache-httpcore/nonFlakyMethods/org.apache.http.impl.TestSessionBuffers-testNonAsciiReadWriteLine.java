@Test public void testNonAsciiReadWriteLine() throws Exception {
  String s1=constructString(SWISS_GERMAN_HELLO);
  HttpParams params=new BasicHttpParams();
  HttpProtocolParams.setHttpElementCharset(params,HTTP.ISO_8859_1);
  SessionOutputBufferMock outbuffer=new SessionOutputBufferMock(params);
  CharArrayBuffer chbuffer=new CharArrayBuffer(16);
  for (int i=0; i < 5; i++) {
    chbuffer.clear();
    chbuffer.append(s1);
    outbuffer.writeLine(chbuffer);
  }
  for (int i=0; i < 5; i++) {
    outbuffer.writeLine(s1);
  }
  outbuffer.flush();
  long bytesWritten=outbuffer.getMetrics().getBytesTransferred();
  long expected=((s1.toString().getBytes(HTTP.ISO_8859_1).length + 2)) * 10;
  Assert.assertEquals(expected,bytesWritten);
  SessionInputBufferMock inbuffer=new SessionInputBufferMock(outbuffer.getData(),params);
  HttpProtocolParams.setHttpElementCharset(params,HTTP.ISO_8859_1);
  for (int i=0; i < 10; i++) {
    CharArrayBuffer buf=new CharArrayBuffer(64);
    int len=inbuffer.readLine(buf);
    Assert.assertEquals(len,SWISS_GERMAN_HELLO.length);
    Assert.assertEquals(s1,buf.toString());
  }
  Assert.assertNull(inbuffer.readLine());
  Assert.assertNull(inbuffer.readLine());
  long bytesRead=inbuffer.getMetrics().getBytesTransferred();
  Assert.assertEquals(expected,bytesRead);
}
