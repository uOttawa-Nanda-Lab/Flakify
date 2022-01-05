@Test public void testNewInstance() throws Exception {
  CommandContext context=CommandContextFactory.newInstance("test");
  assertThat(context.getCommandName(),equalTo("test"));
  context=CommandContextFactory.newInstance("command",new String[]{"hello"},true);
  assertThat(context.getCommandName(),equalTo("command"));
  assertThat(context.getArgs(),Matchers.arrayContaining("hello"));
  assertTrue(context.isHttp());
}
