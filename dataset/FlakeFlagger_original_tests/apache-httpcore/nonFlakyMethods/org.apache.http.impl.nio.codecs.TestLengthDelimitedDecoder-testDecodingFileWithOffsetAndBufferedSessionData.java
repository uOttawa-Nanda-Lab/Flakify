@Test public void testDecodingFileWithOffsetAndBufferedSessionData() throws Exception {
  ReadableByteChannel channel=new ReadableByteChannelMock(new String[]{"stuff; ","more stuff; ","a lot more stuff!"},"US-ASCII");
  HttpParams params=new BasicHttpParams();
  SessionInputBuffer inbuf=new SessionInputBufferImpl(1024,256,params);
  HttpTransportMetricsImpl metrics=new HttpTransportMetricsImpl();
  LengthDelimitedDecoder decoder=new LengthDelimitedDecoder(channel,inbuf,metrics,36);
  int i=inbuf.fill(channel);
  Assert.assertEquals(7,i);
  byte[] beginning="beginning; ".getBytes("US-ASCII");
  createTempFile();
  RandomAccessFile testfile=new RandomAccessFile(this.tmpfile,"rw");
  try {
    testfile.write(beginning);
  }
  finally {
    testfile.close();
  }
  testfile=new RandomAccessFile(this.tmpfile,"rw");
  try {
    FileChannel fchannel=testfile.getChannel();
    long pos=beginning.length;
    while (!decoder.isCompleted()) {
      if (testfile.length() < pos)       testfile.setLength(pos);
      long bytesRead=decoder.transfer(fchannel,pos,10);
      if (bytesRead > 0) {
        pos+=bytesRead;
      }
    }
  }
  finally {
    testfile.close();
  }
  Assert.assertEquals(this.tmpfile.length() - 7 - beginning.length,metrics.getBytesTransferred());
  Assert.assertEquals("beginning; stuff; more stuff; a lot more stuff!",readFromFile(this.tmpfile));
}
