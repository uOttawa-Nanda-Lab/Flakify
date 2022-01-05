@Test public void testDecodeExceptionResponse() throws Exception {
  URL url=URL.valueOf(ThriftProtocol.NAME + "://127.0.0.1:40880/" + Demo.class.getName());
  Channel channel=new MockedChannel(url);
  RandomAccessByteArrayOutputStream bos=new RandomAccessByteArrayOutputStream(128);
  Request request=createRequest();
  DefaultFuture future=DefaultFuture.newFuture(channel,request,10);
  TMessage message=new TMessage("echoString",TMessageType.EXCEPTION,ThriftCodec.getSeqId());
  TTransport transport=new TIOStreamTransport(bos);
  TBinaryProtocol protocol=new TBinaryProtocol(transport);
  TApplicationException exception=new TApplicationException();
  int messageLength, headerLength;
  protocol.writeI16(ThriftCodec.MAGIC);
  protocol.writeI32(Integer.MAX_VALUE);
  protocol.writeI16(Short.MAX_VALUE);
  protocol.writeByte(ThriftCodec.VERSION);
  protocol.writeString(Demo.class.getName());
  protocol.writeI64(request.getId());
  protocol.getTransport().flush();
  headerLength=bos.size();
  protocol.writeMessageBegin(message);
  exception.write(protocol);
  protocol.writeMessageEnd();
  protocol.getTransport().flush();
  int oldIndex=messageLength=bos.size();
  try {
    bos.setWriteIndex(ThriftCodec.MESSAGE_LENGTH_INDEX);
    protocol.writeI32(messageLength);
    bos.setWriteIndex(ThriftCodec.MESSAGE_HEADER_LENGTH_INDEX);
    protocol.writeI16((short)(0xffff & headerLength));
  }
  finally {
    bos.setWriteIndex(oldIndex);
  }
  ChannelBuffer bis=ChannelBuffers.wrappedBuffer(encodeFrame(bos.toByteArray()));
  Object obj=codec.decode((Channel)null,bis);
  Assert.assertNotNull(obj);
  Assert.assertTrue(obj instanceof Response);
  Response response=(Response)obj;
  Assert.assertTrue(response.getResult() instanceof RpcResult);
  RpcResult result=(RpcResult)response.getResult();
  Assert.assertTrue(result.hasException());
  Assert.assertTrue(result.getException() instanceof RpcException);
}
