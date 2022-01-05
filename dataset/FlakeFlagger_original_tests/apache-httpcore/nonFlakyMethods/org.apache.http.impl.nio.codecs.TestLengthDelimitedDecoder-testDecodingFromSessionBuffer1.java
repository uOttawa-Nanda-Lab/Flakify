@Test public void testDecodingFromSessionBuffer1() throws Exception {
  ReadableByteChannel channel=new ReadableByteChannelMock(new String[]{"stuff;","more stuff"},"US-ASCII");
  HttpParams params=new BasicHttpParams();
  SessionInputBuffer inbuf=new SessionInputBufferImpl(1024,256,params);
  HttpTransportMetricsImpl metrics=new HttpTransportMetricsImpl();
  inbuf.fill(channel);
  Assert.assertEquals(6,inbuf.length());
  LengthDelimitedDecoder decoder=new LengthDelimitedDecoder(channel,inbuf,metrics,16);
  ByteBuffer dst=ByteBuffer.allocate(1024);
  int bytesRead=decoder.read(dst);
  Assert.assertEquals(6,bytesRead);
  Assert.assertEquals("stuff;",convert(dst));
  Assert.assertFalse(decoder.isCompleted());
  Assert.assertEquals(0,metrics.getBytesTransferred());
  dst.clear();
  bytesRead=decoder.read(dst);
  Assert.assertEquals(10,bytesRead);
  Assert.assertEquals("more stuff",convert(dst));
  Assert.assertTrue(decoder.isCompleted());
  Assert.assertEquals(10,metrics.getBytesTransferred());
  dst.clear();
  bytesRead=decoder.read(dst);
  Assert.assertEquals(-1,bytesRead);
  Assert.assertTrue(decoder.isCompleted());
  Assert.assertEquals(10,metrics.getBytesTransferred());
}
