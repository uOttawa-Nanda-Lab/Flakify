@Test public void testMessageLengthGreaterThanMessageActualLength() throws Exception {
  Channel channel=getCliendSideChannel(url);
  Request request=new Request(1L);
  request.setVersion(Version.getProtocolVersion());
  Date date=new Date();
  request.setData(date);
  ChannelBuffer encodeBuffer=ChannelBuffers.dynamicBuffer(1024);
  codec.encode(channel,encodeBuffer,request);
  byte[] bytes=new byte[encodeBuffer.writerIndex()];
  encodeBuffer.readBytes(bytes);
  int len=Bytes.bytes2int(bytes,12);
  ByteArrayOutputStream out=new ByteArrayOutputStream(1024);
  out.write(bytes,0,12);
  int padding=512;
  out.write(Bytes.int2bytes(len + padding));
  out.write(bytes,16,bytes.length - 16);
  for (int i=0; i < padding; i++) {
    out.write(1);
  }
  out.write(bytes);
  ChannelBuffer decodeBuffer=ChannelBuffers.wrappedBuffer(out.toByteArray());
  Request decodedRequest=(Request)codec.decode(channel,decodeBuffer);
  Assert.assertTrue(date.equals(decodedRequest.getData()));
  Assert.assertEquals(bytes.length + padding,decodeBuffer.readerIndex());
  decodedRequest=(Request)codec.decode(channel,decodeBuffer);
  Assert.assertTrue(date.equals(decodedRequest.getData()));
}
