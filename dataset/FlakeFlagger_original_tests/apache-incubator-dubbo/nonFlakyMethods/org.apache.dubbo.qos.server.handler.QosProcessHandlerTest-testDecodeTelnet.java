@Test public void testDecodeTelnet() throws Exception {
  ByteBuf buf=Unpooled.wrappedBuffer(new byte[]{'A'});
  ChannelHandlerContext context=Mockito.mock(ChannelHandlerContext.class);
  ChannelPipeline pipeline=Mockito.mock(ChannelPipeline.class);
  Mockito.when(context.pipeline()).thenReturn(pipeline);
  QosProcessHandler handler=new QosProcessHandler("welcome",false);
  handler.decode(context,buf,Collections.emptyList());
  verify(pipeline).addLast(any(LineBasedFrameDecoder.class));
  verify(pipeline).addLast(any(StringDecoder.class));
  verify(pipeline).addLast(any(StringEncoder.class));
  verify(pipeline).addLast(any(TelnetProcessHandler.class));
  verify(pipeline).remove(handler);
}
