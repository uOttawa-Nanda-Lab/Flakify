@Test public void testPrompt() throws Exception {
  ChannelHandlerContext context=mock(ChannelHandlerContext.class);
  TelnetProcessHandler handler=new TelnetProcessHandler();
  handler.channelRead0(context,"");
  verify(context).writeAndFlush(QosProcessHandler.prompt);
}
