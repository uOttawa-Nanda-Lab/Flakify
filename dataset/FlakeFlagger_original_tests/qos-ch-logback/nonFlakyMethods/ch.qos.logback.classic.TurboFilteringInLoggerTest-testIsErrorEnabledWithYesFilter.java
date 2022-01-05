@Test public void testIsErrorEnabledWithYesFilter(){
  addYesFilter();
  logger.setLevel(Level.OFF);
  assertTrue(logger.isErrorEnabled());
}
