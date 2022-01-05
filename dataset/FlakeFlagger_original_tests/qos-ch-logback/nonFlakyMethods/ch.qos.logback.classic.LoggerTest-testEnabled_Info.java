@Test public void testEnabled_Info() throws Exception {
  root.setLevel(Level.INFO);
  checkLevelThreshold(loggerTest,Level.INFO);
}
