@Test public void testHasCommand() throws Exception {
  assertTrue(CommandHelper.hasCommand("greeting"));
  assertFalse(CommandHelper.hasCommand("not-exiting"));
}
