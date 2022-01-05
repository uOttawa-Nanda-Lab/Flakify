@Test public void testBasicReadWriteLine() throws Exception {
  String[] teststrs=new String[5];
  teststrs[0]="Hello";
  teststrs[1]="This string should be much longer than the size of the output buffer " + "which is only 16 bytes for this test";
  StringBuilder buffer=new StringBuilder();
  for (int i=0; i < 15; i++) {
    buffer.append("123456789 ");
  }
  buffer.append("and stuff like that");
  teststrs[2]=buffer.toString();
  teststrs[3]="";
  teststrs[4]="And goodbye";
  CharArrayBuffer chbuffer=new CharArrayBuffer(16);
  SessionOutputBufferMock outbuffer=new SessionOutputBufferMock();
  for (int i=0; i < teststrs.length; i++) {
    chbuffer.clear();
    chbuffer.append(teststrs[i]);
    outbuffer.writeLine(chbuffer);
  }
  outbuffer.writeLine((String)null);
  outbuffer.writeLine((CharArrayBuffer)null);
  outbuffer.flush();
  HttpTransportMetrics tmetrics=outbuffer.getMetrics();
  long bytesWritten=tmetrics.getBytesTransferred();
  long expected=0;
  for (int i=0; i < teststrs.length; i++) {
    expected+=(teststrs[i].length() + 2);
  }
  Assert.assertEquals(expected,bytesWritten);
  SessionInputBufferMock inbuffer=new SessionInputBufferMock(outbuffer.getData());
  for (int i=0; i < teststrs.length; i++) {
    Assert.assertEquals(teststrs[i],inbuffer.readLine());
  }
  Assert.assertNull(inbuffer.readLine());
  Assert.assertNull(inbuffer.readLine());
  tmetrics=inbuffer.getMetrics();
  long bytesRead=tmetrics.getBytesTransferred();
  Assert.assertEquals(expected,bytesRead);
}
