@Test public void noFileFound() throws JoranException {
  System.setProperty(INCLUDE_KEY,"toto");
  tc.doConfigure(TOP_BY_FILE);
  assertEquals(Status.ERROR,statusChecker.getHighestLevel(0));
  assertTrue(statusChecker.containsException(FileNotFoundException.class));
}
