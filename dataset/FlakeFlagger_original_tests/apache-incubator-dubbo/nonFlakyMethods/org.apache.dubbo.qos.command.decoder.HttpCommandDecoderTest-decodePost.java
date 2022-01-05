@Test public void decodePost() throws Exception {
  FullHttpRequest request=mock(FullHttpRequest.class);
  when(request.getUri()).thenReturn("localhost:80/test");
  when(request.getMethod()).thenReturn(HttpMethod.POST);
  when(request.headers()).thenReturn(HttpHeaders.EMPTY_HEADERS);
  ByteBuf buf=Unpooled.copiedBuffer("a=b&c=d",StandardCharsets.UTF_8);
  when(request.content()).thenReturn(buf);
  CommandContext context=HttpCommandDecoder.decode(request);
  assertThat(context.getCommandName(),equalTo("test"));
  assertThat(context.isHttp(),is(true));
  assertThat(context.getArgs(),arrayContaining("b","d"));
}
