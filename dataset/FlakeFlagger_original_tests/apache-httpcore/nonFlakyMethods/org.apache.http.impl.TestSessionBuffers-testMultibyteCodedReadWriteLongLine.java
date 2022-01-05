@Test public void testMultibyteCodedReadWriteLongLine() throws Exception {
  String s1=constructString(SWISS_GERMAN_HELLO);
  String s2=constructString(RUSSIAN_HELLO);
  String s3="Like hello and stuff";
  StringBuilder buf=new StringBuilder();
  for (int i=0; i < 1024; i++) {
    buf.append(s1).append(s2).append(s3);
  }
  String s=buf.toString();
  HttpParams params=new BasicHttpParams();
  HttpProtocolParams.setHttpElementCharset(params,"UTF-8");
  SessionOutputBufferMock outbuffer=new SessionOutputBufferMock(params);
  CharArrayBuffer chbuffer=new CharArrayBuffer(16);
  chbuffer.append(s);
  outbuffer.writeLine(chbuffer);
  outbuffer.flush();
  SessionInputBufferMock inbuffer=new SessionInputBufferMock(outbuffer.getData(),params);
  Assert.assertEquals(s,inbuffer.readLine());
}
