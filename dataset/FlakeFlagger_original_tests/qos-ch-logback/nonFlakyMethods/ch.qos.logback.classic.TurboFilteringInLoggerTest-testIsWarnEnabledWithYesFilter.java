@Test public void testIsWarnEnabledWithYesFilter(){
  addYesFilter();
  logger.setLevel(Level.ERROR);
  assertTrue(logger.isWarnEnabled());
}
