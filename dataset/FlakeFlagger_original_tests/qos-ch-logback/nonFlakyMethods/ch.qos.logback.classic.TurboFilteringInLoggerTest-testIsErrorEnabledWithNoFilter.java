@Test public void testIsErrorEnabledWithNoFilter(){
  addNoFilter();
  logger.setLevel(Level.DEBUG);
  assertFalse(logger.isErrorEnabled());
}
