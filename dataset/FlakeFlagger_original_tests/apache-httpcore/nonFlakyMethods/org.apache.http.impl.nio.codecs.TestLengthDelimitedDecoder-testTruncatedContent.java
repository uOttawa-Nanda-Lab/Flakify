@Test(expected=ConnectionClosedException.class) public void testTruncatedContent() throws Exception {
  ReadableByteChannel channel=new ReadableByteChannelMock(new String[]{"1234567890"},"US-ASCII");
  HttpParams params=new BasicHttpParams();
  SessionInputBuffer inbuf=new SessionInputBufferImpl(1024,256,params);
  HttpTransportMetricsImpl metrics=new HttpTransportMetricsImpl();
  LengthDelimitedDecoder decoder=new LengthDelimitedDecoder(channel,inbuf,metrics,20);
  ByteBuffer dst=ByteBuffer.allocate(1024);
  int bytesRead=decoder.read(dst);
  Assert.assertEquals(10,bytesRead);
  decoder.read(dst);
}
