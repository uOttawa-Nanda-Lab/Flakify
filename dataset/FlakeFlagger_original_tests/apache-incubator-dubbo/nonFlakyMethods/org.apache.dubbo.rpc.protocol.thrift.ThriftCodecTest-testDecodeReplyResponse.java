@Test public void testDecodeReplyResponse() throws Exception {
  URL url=URL.valueOf(ThriftProtocol.NAME + "://127.0.0.1:40880/" + Demo.Iface.class.getName());
  Channel channel=new MockedChannel(url);
  RandomAccessByteArrayOutputStream bos=new RandomAccessByteArrayOutputStream(128);
  Request request=createRequest();
  DefaultFuture future=DefaultFuture.newFuture(channel,request,10);
  TMessage message=new TMessage("echoString",TMessageType.REPLY,ThriftCodec.getSeqId());
  Demo.echoString_result methodResult=new Demo.echoString_result();
  methodResult.success="Hello, World!";
  TTransport transport=new TIOStreamTransport(bos);
  TBinaryProtocol protocol=new TBinaryProtocol(transport);
  int messageLength, headerLength;
  protocol.writeI16(ThriftCodec.MAGIC);
  protocol.writeI32(Integer.MAX_VALUE);
  protocol.writeI16(Short.MAX_VALUE);
  protocol.writeByte(ThriftCodec.VERSION);
  protocol.writeString(Demo.Iface.class.getName());
  protocol.writeI64(request.getId());
  protocol.getTransport().flush();
  headerLength=bos.size();
  protocol.writeMessageBegin(message);
  methodResult.write(protocol);
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
  byte[] buf=new byte[4 + bos.size()];
  System.arraycopy(bos.toByteArray(),0,buf,4,bos.size());
  ChannelBuffer bis=ChannelBuffers.wrappedBuffer(buf);
  Object obj=codec.decode((Channel)null,bis);
  Assert.assertNotNull(obj);
  Assert.assertEquals(true,obj instanceof Response);
  Response response=(Response)obj;
  Assert.assertEquals(request.getId(),response.getId());
  Assert.assertTrue(response.getResult() instanceof RpcResult);
  RpcResult result=(RpcResult)response.getResult();
  Assert.assertTrue(result.getResult() instanceof String);
  Assert.assertEquals(methodResult.success,result.getResult());
}
