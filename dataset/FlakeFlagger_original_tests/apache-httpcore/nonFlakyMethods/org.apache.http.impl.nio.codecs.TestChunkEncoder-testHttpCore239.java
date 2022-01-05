@Test public void testHttpCore239() throws Exception {
  FixedByteChannel channel=new FixedByteChannel(16);
  HttpParams params=new BasicHttpParams();
  SessionOutputBuffer outbuf=new SessionOutputBufferImpl(16,16,params);
  HttpTransportMetricsImpl metrics=new HttpTransportMetricsImpl();
  ChunkEncoder encoder=new ChunkEncoder(channel,outbuf,metrics);
  channel.write(wrap("0123456789ABCDEF"));
  outbuf.write(wrap("0123456789ABCDEF"));
  ByteBuffer src=wrap("0123456789ABCDEF");
  Assert.assertEquals(0,encoder.write(src));
  Assert.assertEquals(0,encoder.write(src));
  Assert.assertEquals(0,encoder.write(src));
  channel.reset();
  outbuf.flush(channel);
  channel.reset();
  Assert.assertEquals(4,encoder.write(src));
  channel.flush();
  Assert.assertEquals(4,encoder.write(src));
  channel.flush();
  Assert.assertEquals(4,encoder.write(src));
  channel.flush();
  Assert.assertEquals(4,encoder.write(src));
  channel.flush();
  Assert.assertEquals(0,encoder.write(src));
  outbuf.flush(channel);
  String s=channel.toString("US-ASCII");
  Assert.assertEquals("4\r\n0123\r\n4\r\n4567\r\n4\r\n89AB\r\n4\r\nCDEF\r\n",s);
}
