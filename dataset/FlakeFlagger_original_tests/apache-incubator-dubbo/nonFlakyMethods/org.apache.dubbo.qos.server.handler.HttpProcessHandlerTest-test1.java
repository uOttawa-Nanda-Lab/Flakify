@Test public void test1() throws Exception {
  ChannelHandlerContext context=mock(ChannelHandlerContext.class);
  ChannelFuture future=mock(ChannelFuture.class);
  when(context.writeAndFlush(any(FullHttpResponse.class))).thenReturn(future);
  HttpRequest message=Mockito.mock(HttpRequest.class);
  when(message.getUri()).thenReturn("test");
  HttpProcessHandler handler=new HttpProcessHandler();
  handler.channelRead0(context,message);
  verify(future).addListener(ChannelFutureListener.CLOSE);
  ArgumentCaptor<FullHttpResponse> captor=ArgumentCaptor.forClass(FullHttpResponse.class);
  verify(context).writeAndFlush(captor.capture());
  FullHttpResponse response=captor.getValue();
  assertThat(response.getStatus().code(),equalTo(404));
}
