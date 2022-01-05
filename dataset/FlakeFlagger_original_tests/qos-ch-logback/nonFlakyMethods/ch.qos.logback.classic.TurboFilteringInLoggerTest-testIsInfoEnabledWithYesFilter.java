@Test public void testIsInfoEnabledWithYesFilter(){
  addYesFilter();
  logger.setLevel(Level.WARN);
  assertTrue(logger.isInfoEnabled());
}
