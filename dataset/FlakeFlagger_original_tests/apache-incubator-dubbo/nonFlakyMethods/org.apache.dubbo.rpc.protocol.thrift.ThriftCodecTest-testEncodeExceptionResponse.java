@Test public void testEncodeExceptionResponse() throws Exception {
  URL url=URL.valueOf(ThriftProtocol.NAME + "://127.0.0.1:40880/" + Demo.Iface.class.getName());
  Channel channel=new MockedChannel(url);
  Request request=createRequest();
  RpcResult rpcResult=new RpcResult();
  String exceptionMessage="failed";
  rpcResult.setException(new RuntimeException(exceptionMessage));
  Response response=new Response();
  response.setResult(rpcResult);
  response.setId(request.getId());
  ChannelBuffer bos=ChannelBuffers.dynamicBuffer(1024);
  ThriftCodec.RequestData rd=ThriftCodec.RequestData.create(ThriftCodec.getSeqId(),Demo.Iface.class.getName(),"echoString");
  ThriftCodec.cachedRequest.put(request.getId(),rd);
  codec.encode(channel,bos,response);
  byte[] buf=new byte[bos.writerIndex() - 4];
  System.arraycopy(bos.array(),4,buf,0,bos.writerIndex() - 4);
  ByteArrayInputStream bis=new ByteArrayInputStream(buf);
  if (bis.markSupported()) {
    bis.mark(0);
  }
  TIOStreamTransport transport=new TIOStreamTransport(bis);
  TBinaryProtocol protocol=new TBinaryProtocol(transport);
  Assert.assertEquals(ThriftCodec.MAGIC,protocol.readI16());
  Assert.assertEquals(protocol.readI32() + 4,bos.writerIndex());
  int headerLength=protocol.readI16();
  Assert.assertEquals(ThriftCodec.VERSION,protocol.readByte());
  Assert.assertEquals(Demo.Iface.class.getName(),protocol.readString());
  Assert.assertEquals(request.getId(),protocol.readI64());
  if (bis.markSupported()) {
    bis.reset();
    bis.skip(headerLength);
  }
  TMessage message=protocol.readMessageBegin();
  Assert.assertEquals("echoString",message.name);
  Assert.assertEquals(TMessageType.EXCEPTION,message.type);
  Assert.assertEquals(ThriftCodec.getSeqId(),message.seqid);
  TApplicationException exception=TApplicationException.read(protocol);
  protocol.readMessageEnd();
  Assert.assertEquals(exceptionMessage,exception.getMessage());
}
