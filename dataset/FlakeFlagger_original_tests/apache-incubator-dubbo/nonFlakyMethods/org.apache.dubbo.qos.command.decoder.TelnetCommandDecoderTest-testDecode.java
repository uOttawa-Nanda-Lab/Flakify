@Test public void testDecode() throws Exception {
  CommandContext context=TelnetCommandDecoder.decode("test a b");
  assertThat(context.getCommandName(),equalTo("test"));
  assertThat(context.isHttp(),is(false));
  assertThat(context.getArgs(),arrayContaining("a","b"));
}
