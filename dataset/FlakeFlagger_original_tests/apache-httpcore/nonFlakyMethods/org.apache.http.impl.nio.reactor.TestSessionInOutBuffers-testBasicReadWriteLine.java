@Test public void testBasicReadWriteLine() throws Exception {
  String[] teststrs=new String[5];
  teststrs[0]="Hello";
  teststrs[1]="This string should be much longer than the size of the line buffer " + "which is only 16 bytes for this test";
  StringBuilder buffer=new StringBuilder();
  for (int i=0; i < 15; i++) {
    buffer.append("123456789 ");
  }
  buffer.append("and stuff like that");
  teststrs[2]=buffer.toString();
  teststrs[3]="";
  teststrs[4]="And goodbye";
  HttpParams params=new BasicHttpParams();
  SessionOutputBuffer outbuf=new SessionOutputBufferImpl(1024,16,params);
  for (int i=0; i < teststrs.length; i++) {
    outbuf.writeLine(teststrs[i]);
  }
  outbuf.writeLine((String)null);
  outbuf.writeLine((CharArrayBuffer)null);
  ByteArrayOutputStream outstream=new ByteArrayOutputStream();
  WritableByteChannel outChannel=newChannel(outstream);
  outbuf.flush(outChannel);
  ReadableByteChannel channel=newChannel(outstream.toByteArray());
  SessionInputBuffer inbuf=new SessionInputBufferImpl(1024,16,params);
  inbuf.fill(channel);
  for (int i=0; i < teststrs.length; i++) {
    Assert.assertEquals(teststrs[i],inbuf.readLine(true));
  }
  Assert.assertNull(inbuf.readLine(true));
  Assert.assertNull(inbuf.readLine(true));
}
