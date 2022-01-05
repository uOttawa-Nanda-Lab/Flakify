@Test public void testGreeting() throws Exception {
  Help help=new Help();
  String output=help.execute(Mockito.mock(CommandContext.class),new String[]{"greeting"});
  assertThat(output,containsString("COMMAND NAME"));
  assertThat(output,containsString("greeting"));
  assertThat(output,containsString("EXAMPLE"));
  assertThat(output,containsString("greeting dubbo"));
}
