@Test public void testBasicFiltering() throws Exception {
  listAppender.start();
  root.addAppender(listAppender);
  root.setLevel(Level.INFO);
  loggerTest.debug("x");
  assertEquals(0,listAppender.list.size());
  loggerTest.info("x");
  loggerTest.warn("x");
  loggerTest.error("x");
  assertEquals(3,listAppender.list.size());
}
