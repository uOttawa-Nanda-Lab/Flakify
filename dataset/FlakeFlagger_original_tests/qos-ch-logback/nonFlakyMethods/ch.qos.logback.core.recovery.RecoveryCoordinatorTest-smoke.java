@Test public void smoke(){
  rc.setCurrentTime(now + RecoveryCoordinator.BACKOFF_COEFFICIENT_MIN + 1);
  assertFalse(rc.isTooSoon());
}
