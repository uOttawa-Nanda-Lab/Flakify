@Test public void testIsEnabledForWithYesFilter(){
  addYesFilter();
  logger.setLevel(Level.ERROR);
  assertTrue(logger.isEnabledFor(Level.INFO));
}
