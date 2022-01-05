@Test public void testMultibyteCodedReadWriteLine() throws Exception {
  String s1=constructString(SWISS_GERMAN_HELLO);
  String s2=constructString(RUSSIAN_HELLO);
  String s3="Like hello and stuff";
  HttpParams params=new BasicHttpParams();
  HttpProtocolParams.setHttpElementCharset(params,"UTF-8");
  SessionOutputBuffer outbuf=new SessionOutputBufferImpl(1024,16,params);
  for (int i=0; i < 10; i++) {
    outbuf.writeLine(s1);
    outbuf.writeLine(s2);
    outbuf.writeLine(s3);
  }
  ByteArrayOutputStream outstream=new ByteArrayOutputStream();
  WritableByteChannel outChannel=newChannel(outstream);
  outbuf.flush(outChannel);
  byte[] tmp=outstream.toByteArray();
  ReadableByteChannel channel=newChannel(tmp);
  SessionInputBuffer inbuf=new SessionInputBufferImpl(16,16,params);
  while (inbuf.fill(channel) > 0) {
  }
  for (int i=0; i < 10; i++) {
    Assert.assertEquals(s1,inbuf.readLine(true));
    Assert.assertEquals(s2,inbuf.readLine(true));
    Assert.assertEquals(s3,inbuf.readLine(true));
  }
}
