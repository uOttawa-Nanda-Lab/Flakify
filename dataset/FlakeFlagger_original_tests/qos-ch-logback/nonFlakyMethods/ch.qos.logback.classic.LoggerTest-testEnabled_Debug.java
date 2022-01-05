@Test public void testEnabled_Debug() throws Exception {
  root.setLevel(Level.DEBUG);
  checkLevelThreshold(loggerTest,Level.DEBUG);
}
