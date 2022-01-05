@Test public void testOutputBufferOperations() throws IOException {
  ByteArrayOutputStream outstream=new ByteArrayOutputStream();
  WritableByteChannel channel=Channels.newChannel(outstream);
  HttpParams params=new BasicHttpParams();
  SessionOutputBuffer outbuf=new SessionOutputBufferImpl(1024,128,params);
  HttpTransportMetricsImpl metrics=new HttpTransportMetricsImpl();
  ContentEncoder encoder=new ContentEncoderMock(channel,outbuf,metrics);
  SimpleOutputBuffer buffer=new SimpleOutputBuffer(4,new DirectByteBufferAllocator());
  buffer.write(EncodingUtils.getAsciiBytes("stuff"));
  buffer.write(';');
  buffer.produceContent(encoder);
  buffer.write(EncodingUtils.getAsciiBytes("more "));
  buffer.write(EncodingUtils.getAsciiBytes("stuff"));
  buffer.produceContent(encoder);
  byte[] content=outstream.toByteArray();
  Assert.assertEquals("stuff;more stuff",EncodingUtils.getAsciiString(content));
}
