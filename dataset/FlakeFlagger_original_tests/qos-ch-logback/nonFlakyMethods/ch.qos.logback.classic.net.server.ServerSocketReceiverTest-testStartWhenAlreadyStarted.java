@Test public void testStartWhenAlreadyStarted() throws Exception {
  receiver.start();
  receiver.start();
  assertEquals(1,runner.getStartCount());
}
