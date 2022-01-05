@Test public void testLogLevel() throws Exception {
  Log log=new Log();
  log.setLogLevel(Level.ALL);
  assertThat(log.getLogLevel(),is(Level.ALL));
}
