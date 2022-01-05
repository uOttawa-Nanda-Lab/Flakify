@Test public void testDecodeRequest() throws Exception {
  Request request=createRequest();
  RandomAccessByteArrayOutputStream bos=new RandomAccessByteArrayOutputStream(1024);
  TIOStreamTransport transport=new TIOStreamTransport(bos);
  TBinaryProtocol protocol=new TBinaryProtocol(transport);
  int messageLength, headerLength;
  protocol.writeI16(ThriftCodec.MAGIC);
  protocol.writeI32(Integer.MAX_VALUE);
  protocol.writeI16(Short.MAX_VALUE);
  protocol.writeByte(ThriftCodec.VERSION);
  protocol.writeString(((RpcInvocation)request.getData()).getAttachment(Constants.INTERFACE_KEY));
  protocol.writeI64(request.getId());
  protocol.getTransport().flush();
  headerLength=bos.size();
  Demo.echoString_args args=new Demo.echoString_args();
  args.setArg("Hell, World!");
  TMessage message=new TMessage("echoString",TMessageType.CALL,ThriftCodec.getSeqId());
  protocol.writeMessageBegin(message);
  args.write(protocol);
  protocol.writeMessageEnd();
  protocol.getTransport().flush();
  int oldIndex=messageLength=bos.size();
  try {
    bos.setWriteIndex(ThriftCodec.MESSAGE_HEADER_LENGTH_INDEX);
    protocol.writeI16((short)(0xffff & headerLength));
    bos.setWriteIndex(ThriftCodec.MESSAGE_LENGTH_INDEX);
    protocol.writeI32(messageLength);
  }
  finally {
    bos.setWriteIndex(oldIndex);
  }
  Object obj=codec.decode((Channel)null,ChannelBuffers.wrappedBuffer(encodeFrame(bos.toByteArray())));
  Assert.assertTrue(obj instanceof Request);
  obj=((Request)obj).getData();
  Assert.assertTrue(obj instanceof RpcInvocation);
  RpcInvocation invocation=(RpcInvocation)obj;
  Assert.assertEquals("echoString",invocation.getMethodName());
  Assert.assertArrayEquals(new Class[]{String.class},invocation.getParameterTypes());
  Assert.assertArrayEquals(new Object[]{args.getArg()},invocation.getArguments());
}
