@Test public void testGetCommandClass() throws Exception {
  assertThat(CommandHelper.getCommandClass("greeting"),equalTo(GreetingCommand.class));
  assertNull(CommandHelper.getCommandClass("not-exiting"));
}
