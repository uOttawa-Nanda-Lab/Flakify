@Test public void testIsInfoEnabledWithNoFilter(){
  addNoFilter();
  logger.setLevel(Level.DEBUG);
  assertFalse(logger.isInfoEnabled());
}
