@Test public void testUnknownCommand() throws Exception {
  ChannelHandlerContext context=mock(ChannelHandlerContext.class);
  TelnetProcessHandler handler=new TelnetProcessHandler();
  handler.channelRead0(context,"unknown");
  ArgumentCaptor<String> captor=ArgumentCaptor.forClass(String.class);
  verify(context,Mockito.atLeastOnce()).writeAndFlush(captor.capture());
  assertThat(captor.getAllValues(),contains("unknown :no such command","\r\ndubbo>"));
}
