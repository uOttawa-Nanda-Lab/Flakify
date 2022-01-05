@Test public void testStopWhenNotStarted() throws Exception {
  receiver.stop();
  assertEquals(0,runner.getStartCount());
}
