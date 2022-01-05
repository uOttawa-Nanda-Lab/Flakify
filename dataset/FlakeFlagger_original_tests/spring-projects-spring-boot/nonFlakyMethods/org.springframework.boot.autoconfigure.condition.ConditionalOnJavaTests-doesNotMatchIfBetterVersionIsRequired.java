@Test public void doesNotMatchIfBetterVersionIsRequired(){
  registerAndRefresh(Java9Required.class);
  assertPresent(false);
}
