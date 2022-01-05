@Test public void test() throws Exception {
  CommandContext context=new CommandContext("test",new String[]{"hello"},true);
  Object request=new Object();
  context.setOrginRequest(request);
  Channel channel=Mockito.mock(Channel.class);
  context.setRemote(channel);
  assertThat(context.getCommandName(),equalTo("test"));
  assertThat(context.getArgs(),arrayContaining("hello"));
  assertThat(context.getOrginRequest(),is(request));
  assertTrue(context.isHttp());
  assertThat(context.getRemote(),is(channel));
  context=new CommandContext("command");
  context.setRemote(channel);
  context.setOrginRequest(request);
  context.setArgs(new String[]{"world"});
  context.setHttp(false);
  assertThat(context.getCommandName(),equalTo("command"));
  assertThat(context.getArgs(),arrayContaining("world"));
  assertThat(context.getOrginRequest(),is(request));
  assertFalse(context.isHttp());
  assertThat(context.getRemote(),is(channel));
}
