@Test public void testExecute() throws Exception {
  Quit quit=new Quit();
  String output=quit.execute(Mockito.mock(CommandContext.class),null);
  assertThat(output,equalTo(QosConstants.CLOSE));
}
