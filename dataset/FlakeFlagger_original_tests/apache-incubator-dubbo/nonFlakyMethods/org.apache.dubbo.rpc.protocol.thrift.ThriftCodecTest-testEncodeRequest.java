@Test public void testEncodeRequest() throws Exception {
  Request request=createRequest();
  ChannelBuffer output=ChannelBuffers.dynamicBuffer(1024);
  codec.encode(channel,output,request);
  byte[] bytes=new byte[output.readableBytes()];
  output.readBytes(bytes);
  ByteArrayInputStream bis=new ByteArrayInputStream(bytes);
  TTransport transport=new TIOStreamTransport(bis);
  TBinaryProtocol protocol=new TBinaryProtocol(transport);
  byte[] length=new byte[4];
  transport.read(length,0,4);
  if (bis.markSupported()) {
    bis.mark(0);
  }
  Assert.assertEquals(ThriftCodec.MAGIC,protocol.readI16());
  int messageLength=protocol.readI32();
  Assert.assertEquals(messageLength + 4,bytes.length);
  short headerLength=protocol.readI16();
  Assert.assertEquals(ThriftCodec.VERSION,protocol.readByte());
  Assert.assertEquals(Demo.Iface.class.getName(),protocol.readString());
  Assert.assertEquals(request.getId(),protocol.readI64());
  if (bis.markSupported()) {
    bis.reset();
    bis.skip(headerLength);
  }
  TMessage message=protocol.readMessageBegin();
  Demo.echoString_args args=new Demo.echoString_args();
  args.read(protocol);
  protocol.readMessageEnd();
  Assert.assertEquals("echoString",message.name);
  Assert.assertEquals(TMessageType.CALL,message.type);
  Assert.assertEquals("Hello, World!",args.getArg());
}
