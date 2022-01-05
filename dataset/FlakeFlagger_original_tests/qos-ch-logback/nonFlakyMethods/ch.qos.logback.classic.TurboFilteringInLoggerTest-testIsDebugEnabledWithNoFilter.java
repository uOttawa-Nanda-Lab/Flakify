@Test public void testIsDebugEnabledWithNoFilter(){
  addNoFilter();
  logger.setLevel(Level.DEBUG);
  assertFalse(logger.isDebugEnabled());
}
