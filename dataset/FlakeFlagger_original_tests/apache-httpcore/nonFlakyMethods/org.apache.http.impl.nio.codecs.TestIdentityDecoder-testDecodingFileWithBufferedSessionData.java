@Test public void testDecodingFileWithBufferedSessionData() throws Exception {
  ReadableByteChannel channel=new ReadableByteChannelMock(new String[]{"stuff; ","more stuff; ","a lot more stuff!"},"US-ASCII");
  HttpParams params=new BasicHttpParams();
  SessionInputBuffer inbuf=new SessionInputBufferImpl(1024,256,params);
  HttpTransportMetricsImpl metrics=new HttpTransportMetricsImpl();
  IdentityDecoder decoder=new IdentityDecoder(channel,inbuf,metrics);
  int i=inbuf.fill(channel);
  Assert.assertEquals(7,i);
  File fileHandle=File.createTempFile("testFile",".txt");
  RandomAccessFile testfile=new RandomAccessFile(fileHandle,"rw");
  FileChannel fchannel=testfile.getChannel();
  long pos=0;
  while (!decoder.isCompleted()) {
    long bytesRead=decoder.transfer(fchannel,pos,10);
    if (bytesRead > 0) {
      pos+=bytesRead;
    }
  }
  Assert.assertEquals(testfile.length() - 7,metrics.getBytesTransferred());
  fchannel.close();
  Assert.assertEquals("stuff; more stuff; a lot more stuff!",readFromFile(fileHandle));
  deleteWithCheck(fileHandle);
}
