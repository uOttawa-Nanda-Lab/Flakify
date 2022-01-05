@Test public void testEnabled_All() throws Exception {
  root.setLevel(Level.ALL);
  checkLevelThreshold(loggerTest,Level.ALL);
}
