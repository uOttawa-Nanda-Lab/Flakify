@Test public void testChunkNoExceed() throws Exception {
  ByteArrayOutputStream baos=new ByteArrayOutputStream();
  WritableByteChannel channel=newChannel(baos);
  HttpParams params=new BasicHttpParams();
  SessionOutputBuffer outbuf=new SessionOutputBufferImpl(1024,16,params);
  HttpTransportMetricsImpl metrics=new HttpTransportMetricsImpl();
  ChunkEncoder encoder=new ChunkEncoder(channel,outbuf,metrics);
  encoder.write(wrap("1234"));
  encoder.complete();
  outbuf.flush(channel);
  String s=baos.toString("US-ASCII");
  Assert.assertTrue(encoder.isCompleted());
  Assert.assertEquals("4\r\n1234\r\n0\r\n\r\n",s);
}
