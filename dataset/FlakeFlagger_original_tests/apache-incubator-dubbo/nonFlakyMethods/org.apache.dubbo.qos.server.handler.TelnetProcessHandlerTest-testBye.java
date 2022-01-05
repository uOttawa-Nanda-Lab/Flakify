@Test public void testBye() throws Exception {
  ChannelHandlerContext context=mock(ChannelHandlerContext.class);
  TelnetProcessHandler handler=new TelnetProcessHandler();
  ChannelFuture future=mock(ChannelFuture.class);
  when(context.writeAndFlush("BYE!\n")).thenReturn(future);
  handler.channelRead0(context,"quit");
  verify(future).addListener(ChannelFutureListener.CLOSE);
}
