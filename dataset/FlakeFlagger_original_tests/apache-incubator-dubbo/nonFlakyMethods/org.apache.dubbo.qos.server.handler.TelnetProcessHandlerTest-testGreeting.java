@Test public void testGreeting() throws Exception {
  ChannelHandlerContext context=mock(ChannelHandlerContext.class);
  TelnetProcessHandler handler=new TelnetProcessHandler();
  handler.channelRead0(context,"greeting");
  ArgumentCaptor<String> captor=ArgumentCaptor.forClass(String.class);
  verify(context).writeAndFlush(captor.capture());
  assertThat(captor.getValue(),containsString("greeting"));
  assertThat(captor.getValue(),containsString("dubbo>"));
}
