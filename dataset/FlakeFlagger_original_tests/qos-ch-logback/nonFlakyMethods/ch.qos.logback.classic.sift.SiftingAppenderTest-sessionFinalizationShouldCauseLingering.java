@Test public void sessionFinalizationShouldCauseLingering() throws JoranException {
  String mdcKey="linger";
  String mdcVal="session" + diff;
  configure(SIFT_FOLDER_PREFIX + "lingering.xml");
  MDC.put(mdcKey,mdcVal);
  logger.debug("linger 1");
  logger.debug(ClassicConstants.FINALIZE_SESSION_MARKER,"linger 2");
  long now=System.currentTimeMillis();
  SiftingAppender sa=(SiftingAppender)root.getAppender("SIFT");
  AppenderTracker<ILoggingEvent> tracker=sa.getAppenderTracker();
  assertEquals(1,tracker.allKeys().size());
  Appender<ILoggingEvent> appender=tracker.find(mdcVal);
  assertTrue(appender.isStarted());
  tracker.removeStaleComponents(now + AppenderTracker.LINGERING_TIMEOUT + 1);
  assertFalse(appender.isStarted());
  assertEquals(0,tracker.allKeys().size());
}
