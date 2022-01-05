@Test public void testCodingEmptyBuffer() throws Exception {
  ByteArrayOutputStream baos=new ByteArrayOutputStream();
  WritableByteChannel channel=newChannel(baos);
  HttpParams params=new BasicHttpParams();
  SessionOutputBuffer outbuf=new SessionOutputBufferImpl(1024,128,params);
  HttpTransportMetricsImpl metrics=new HttpTransportMetricsImpl();
  ChunkEncoder encoder=new ChunkEncoder(channel,outbuf,metrics);
  encoder.write(wrap("12345"));
  encoder.write(wrap("678"));
  encoder.write(wrap("90"));
  ByteBuffer empty=ByteBuffer.allocate(100);
  empty.flip();
  encoder.write(empty);
  encoder.write(null);
  encoder.complete();
  outbuf.flush(channel);
  String s=baos.toString("US-ASCII");
  Assert.assertTrue(encoder.isCompleted());
  Assert.assertEquals("5\r\n12345\r\n3\r\n678\r\n2\r\n90\r\n0\r\n\r\n",s);
}
