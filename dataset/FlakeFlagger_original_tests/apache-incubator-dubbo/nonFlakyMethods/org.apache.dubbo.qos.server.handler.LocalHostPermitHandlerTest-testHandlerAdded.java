@Test public void testHandlerAdded() throws Exception {
  ChannelHandlerContext context=mock(ChannelHandlerContext.class);
  Channel channel=mock(Channel.class);
  when(context.channel()).thenReturn(channel);
  InetAddress addr=mock(InetAddress.class);
  when(addr.isLoopbackAddress()).thenReturn(false);
  InetSocketAddress address=new InetSocketAddress(addr,12345);
  when(channel.remoteAddress()).thenReturn(address);
  ChannelFuture future=mock(ChannelFuture.class);
  when(context.writeAndFlush(any(ByteBuf.class))).thenReturn(future);
  LocalHostPermitHandler handler=new LocalHostPermitHandler(false);
  handler.handlerAdded(context);
  ArgumentCaptor<ByteBuf> captor=ArgumentCaptor.forClass(ByteBuf.class);
  verify(context).writeAndFlush(captor.capture());
  assertThat(new String(captor.getValue().array()),containsString("Foreign Ip Not Permitted"));
  verify(future).addListener(ChannelFutureListener.CLOSE);
}
