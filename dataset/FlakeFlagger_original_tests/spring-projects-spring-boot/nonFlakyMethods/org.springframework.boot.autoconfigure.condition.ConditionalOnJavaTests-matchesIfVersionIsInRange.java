@Test public void matchesIfVersionIsInRange(){
  registerAndRefresh(Java6Required.class);
  assertPresent(true);
}
