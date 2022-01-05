@Test public void actualTime() throws InterruptedException {
  assertTrue(rc.isTooSoon());
  Thread.sleep(RecoveryCoordinator.BACKOFF_COEFFICIENT_MIN + 20);
  assertFalse(rc.isTooSoon());
}
