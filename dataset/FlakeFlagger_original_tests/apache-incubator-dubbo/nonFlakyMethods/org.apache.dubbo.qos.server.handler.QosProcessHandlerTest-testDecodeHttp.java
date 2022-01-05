@Test public void testDecodeHttp() throws Exception {
  ByteBuf buf=Unpooled.wrappedBuffer(new byte[]{'G'});
  ChannelHandlerContext context=Mockito.mock(ChannelHandlerContext.class);
  ChannelPipeline pipeline=Mockito.mock(ChannelPipeline.class);
  Mockito.when(context.pipeline()).thenReturn(pipeline);
  QosProcessHandler handler=new QosProcessHandler("welcome",false);
  handler.decode(context,buf,Collections.emptyList());
  verify(pipeline).addLast(any(HttpServerCodec.class));
  verify(pipeline).addLast(any(HttpObjectAggregator.class));
  verify(pipeline).addLast(any(HttpProcessHandler.class));
  verify(pipeline).remove(handler);
}
