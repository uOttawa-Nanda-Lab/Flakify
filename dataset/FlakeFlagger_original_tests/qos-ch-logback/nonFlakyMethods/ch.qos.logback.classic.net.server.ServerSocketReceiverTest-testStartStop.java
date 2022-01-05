@Test public void testStartStop() throws Exception {
  receiver.start();
  assertTrue(runner.isContextInjected());
  assertTrue(runner.isRunning());
  assertSame(listener,receiver.getLastListener());
  receiver.stop();
  assertFalse(runner.isRunning());
}
