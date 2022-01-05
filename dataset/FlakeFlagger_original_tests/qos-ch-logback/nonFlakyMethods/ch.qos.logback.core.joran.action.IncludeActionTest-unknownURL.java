@Test public void unknownURL() throws JoranException {
  System.setProperty(INCLUDE_KEY,"http://logback2345.qos.ch");
  tc.doConfigure(TOP_BY_URL);
  assertEquals(Status.ERROR,statusChecker.getHighestLevel(0));
  assertTrue(statusChecker.containsException(UnknownHostException.class) || statusChecker.containsException(IOException.class));
}
