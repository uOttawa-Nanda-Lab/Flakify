@Test public void doesNotMatchIfLowerIsRequired(){
  registerAndRefresh(Java5Required.class);
  assertPresent(false);
}
