@Test public void testTurboDynamicThreshold() throws JoranException {
  configure(ClassicTestConstants.JORAN_INPUT_PREFIX + "turboDynamicThreshold.xml");
  ListAppender listAppender=(ListAppender)root.getAppender("LIST");
  assertEquals(0,listAppender.list.size());
  MDC.put("userId","user1");
  logger.debug("hello user1");
  MDC.put("userId","user2");
  logger.debug("hello user2");
  assertEquals(1,listAppender.list.size());
  ILoggingEvent le=(ILoggingEvent)listAppender.list.get(0);
  assertEquals("hello user2",le.getMessage());
}
