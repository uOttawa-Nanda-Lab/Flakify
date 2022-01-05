@Test public void testChunkExceed() throws Exception {
  ByteArrayOutputStream baos=new ByteArrayOutputStream();
  WritableByteChannel channel=newChannel(baos);
  HttpParams params=new BasicHttpParams();
  SessionOutputBuffer outbuf=new SessionOutputBufferImpl(16,16,params);
  HttpTransportMetricsImpl metrics=new HttpTransportMetricsImpl();
  ChunkEncoder encoder=new ChunkEncoder(channel,outbuf,metrics);
  ByteBuffer src=wrap("0123456789ABCDEF");
  Assert.assertEquals(4,encoder.write(src));
  Assert.assertTrue(src.hasRemaining());
  Assert.assertEquals(12,src.remaining());
  Assert.assertEquals(4,encoder.write(src));
  Assert.assertTrue(src.hasRemaining());
  Assert.assertEquals(8,src.remaining());
  Assert.assertEquals(4,encoder.write(src));
  Assert.assertEquals(4,encoder.write(src));
  Assert.assertFalse(src.hasRemaining());
  outbuf.flush(channel);
  String s=baos.toString("US-ASCII");
  Assert.assertEquals("4\r\n0123\r\n4\r\n4567\r\n4\r\n89AB\r\n4\r\nCDEF\r\n",s);
}
