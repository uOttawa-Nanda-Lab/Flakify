@Test public void testIsErrorEnabledWithDenyBlueFilter(){
  addDenyBLUEFilter();
  logger.setLevel(Level.ALL);
  assertFalse(logger.isDebugEnabled(blueMarker));
}
