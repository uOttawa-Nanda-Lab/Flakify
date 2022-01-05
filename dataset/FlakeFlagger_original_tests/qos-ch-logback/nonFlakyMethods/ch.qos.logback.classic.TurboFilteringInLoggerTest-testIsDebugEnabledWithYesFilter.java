@Test public void testIsDebugEnabledWithYesFilter(){
  addYesFilter();
  logger.setLevel(Level.INFO);
  assertTrue(logger.isDebugEnabled());
}
