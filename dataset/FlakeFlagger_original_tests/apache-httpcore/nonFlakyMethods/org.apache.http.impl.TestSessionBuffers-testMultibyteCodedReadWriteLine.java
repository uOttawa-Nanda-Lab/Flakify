@Test public void testMultibyteCodedReadWriteLine() throws Exception {
  String s1=constructString(SWISS_GERMAN_HELLO);
  String s2=constructString(RUSSIAN_HELLO);
  String s3="Like hello and stuff";
  HttpParams params=new BasicHttpParams();
  HttpProtocolParams.setHttpElementCharset(params,"UTF-8");
  SessionOutputBufferMock outbuffer=new SessionOutputBufferMock(params);
  CharArrayBuffer chbuffer=new CharArrayBuffer(16);
  for (int i=0; i < 10; i++) {
    chbuffer.clear();
    chbuffer.append(s1);
    outbuffer.writeLine(chbuffer);
    chbuffer.clear();
    chbuffer.append(s2);
    outbuffer.writeLine(chbuffer);
    chbuffer.clear();
    chbuffer.append(s3);
    outbuffer.writeLine(chbuffer);
  }
  outbuffer.flush();
  long bytesWritten=outbuffer.getMetrics().getBytesTransferred();
  long expected=((s1.getBytes("UTF-8").length + 2) + (s2.getBytes("UTF-8").length + 2) + (s3.getBytes("UTF-8").length + 2)) * 10;
  Assert.assertEquals(expected,bytesWritten);
  SessionInputBufferMock inbuffer=new SessionInputBufferMock(outbuffer.getData(),params);
  for (int i=0; i < 10; i++) {
    Assert.assertEquals(s1,inbuffer.readLine());
    Assert.assertEquals(s2,inbuffer.readLine());
    Assert.assertEquals(s3,inbuffer.readLine());
  }
  Assert.assertNull(inbuffer.readLine());
  Assert.assertNull(inbuffer.readLine());
  long bytesRead=inbuffer.getMetrics().getBytesTransferred();
  Assert.assertEquals(expected,bytesRead);
}
