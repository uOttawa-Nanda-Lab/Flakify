@Test public void testIsErrorEnabledWithAcceptBlueFilter(){
  addAcceptBLUEFilter();
  logger.setLevel(Level.ERROR);
  assertTrue(logger.isDebugEnabled(blueMarker));
}
