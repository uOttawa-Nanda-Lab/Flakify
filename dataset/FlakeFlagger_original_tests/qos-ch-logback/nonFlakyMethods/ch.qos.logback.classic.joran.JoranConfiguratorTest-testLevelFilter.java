@Test public void testLevelFilter() throws JoranException {
  configure(ClassicTestConstants.JORAN_INPUT_PREFIX + "levelFilter.xml");
  logger.warn("hello");
  logger.error("to be ignored");
  @SuppressWarnings("unchecked") ListAppender<ILoggingEvent> listAppender=(ListAppender)root.getAppender("LIST");
  assertNotNull(listAppender);
  assertEquals(1,listAppender.list.size());
  ILoggingEvent back=listAppender.list.get(0);
  assertEquals(Level.WARN,back.getLevel());
  assertEquals("hello",back.getMessage());
}
