@Test public void decodeGet() throws Exception {
  HttpRequest request=mock(HttpRequest.class);
  when(request.getUri()).thenReturn("localhost:80/test");
  when(request.getMethod()).thenReturn(HttpMethod.GET);
  CommandContext context=HttpCommandDecoder.decode(request);
  assertThat(context.getCommandName(),equalTo("test"));
  assertThat(context.isHttp(),is(true));
  when(request.getUri()).thenReturn("localhost:80/test?a=b&c=d");
  context=HttpCommandDecoder.decode(request);
  assertThat(context.getArgs(),arrayContaining("b","d"));
}
