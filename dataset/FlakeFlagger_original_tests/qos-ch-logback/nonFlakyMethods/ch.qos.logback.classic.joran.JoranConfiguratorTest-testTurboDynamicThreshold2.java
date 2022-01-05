@Test public void testTurboDynamicThreshold2() throws JoranException {
  configure(ClassicTestConstants.JORAN_INPUT_PREFIX + "turboDynamicThreshold2.xml");
  ListAppender listAppender=(ListAppender)root.getAppender("LIST");
  assertEquals(0,listAppender.list.size());
  MDC.put("userId","user1");
  logger.debug("hello user1");
  MDC.put("userId","user2");
  logger.debug("hello user2");
  MDC.put("userId","user3");
  logger.debug("hello user3");
  assertEquals(2,listAppender.list.size());
  ILoggingEvent le=(ILoggingEvent)listAppender.list.get(0);
  assertEquals("hello user1",le.getMessage());
  le=(ILoggingEvent)listAppender.list.get(1);
  assertEquals("hello user2",le.getMessage());
}
