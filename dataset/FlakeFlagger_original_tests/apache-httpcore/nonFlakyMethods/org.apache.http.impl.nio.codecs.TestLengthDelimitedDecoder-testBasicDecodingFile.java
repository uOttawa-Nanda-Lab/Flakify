@Test public void testBasicDecodingFile() throws Exception {
  ReadableByteChannel channel=new ReadableByteChannelMock(new String[]{"stuff; ","more stuff; ","a lot more stuff!!!"},"US-ASCII");
  HttpParams params=new BasicHttpParams();
  SessionInputBuffer inbuf=new SessionInputBufferImpl(1024,256,params);
  HttpTransportMetricsImpl metrics=new HttpTransportMetricsImpl();
  LengthDelimitedDecoder decoder=new LengthDelimitedDecoder(channel,inbuf,metrics,36);
  createTempFile();
  RandomAccessFile testfile=new RandomAccessFile(this.tmpfile,"rw");
  try {
    FileChannel fchannel=testfile.getChannel();
    long pos=0;
    while (!decoder.isCompleted()) {
      long bytesRead=decoder.transfer(fchannel,pos,10);
      if (bytesRead > 0) {
        pos+=bytesRead;
      }
    }
  }
  finally {
    testfile.close();
  }
  Assert.assertEquals(this.tmpfile.length(),metrics.getBytesTransferred());
  Assert.assertEquals("stuff; more stuff; a lot more stuff!",readFromFile(this.tmpfile));
}
