@Test(expected=ConnectionClosedException.class) public void testTruncatedContentWithFile() throws Exception {
  ReadableByteChannel channel=new ReadableByteChannelMock(new String[]{"1234567890"},"US-ASCII");
  HttpParams params=new BasicHttpParams();
  SessionInputBuffer inbuf=new SessionInputBufferImpl(1024,256,params);
  HttpTransportMetricsImpl metrics=new HttpTransportMetricsImpl();
  LengthDelimitedDecoder decoder=new LengthDelimitedDecoder(channel,inbuf,metrics,20);
  createTempFile();
  RandomAccessFile testfile=new RandomAccessFile(this.tmpfile,"rw");
  try {
    FileChannel fchannel=testfile.getChannel();
    long bytesRead=decoder.transfer(fchannel,0,Integer.MAX_VALUE);
    Assert.assertEquals(10,bytesRead);
    decoder.transfer(fchannel,0,Integer.MAX_VALUE);
  }
  finally {
    testfile.close();
  }
}
