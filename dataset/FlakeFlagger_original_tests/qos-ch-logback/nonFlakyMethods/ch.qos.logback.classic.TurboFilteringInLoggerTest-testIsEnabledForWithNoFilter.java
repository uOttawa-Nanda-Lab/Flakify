@Test public void testIsEnabledForWithNoFilter(){
  addNoFilter();
  logger.setLevel(Level.DEBUG);
  assertFalse(logger.isEnabledFor(Level.INFO));
}
