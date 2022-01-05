@Test public void testMainHelp() throws Exception {
  Help help=new Help();
  String output=help.execute(Mockito.mock(CommandContext.class),null);
  assertThat(output,containsString("greeting"));
  assertThat(output,containsString("help"));
  assertThat(output,containsString("ls"));
  assertThat(output,containsString("online"));
  assertThat(output,containsString("offline"));
  assertThat(output,containsString("quit"));
}
