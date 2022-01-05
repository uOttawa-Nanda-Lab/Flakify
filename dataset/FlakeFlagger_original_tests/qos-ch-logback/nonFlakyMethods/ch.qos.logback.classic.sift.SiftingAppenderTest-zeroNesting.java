@Test public void zeroNesting() throws JoranException {
  configure(SIFT_FOLDER_PREFIX + "zeroNesting.xml");
  logger.debug("hello");
  logger.debug("hello");
  logger.debug("hello");
  logger.debug("hello");
  logger.debug("hello");
  SiftingAppender sa=(SiftingAppender)root.getAppender("SIFT");
  Appender<ILoggingEvent> appender=getAppenderTracker().find("zeroDefault");
  assertNotNull(appender);
  NOPAppender<ILoggingEvent> nopa=(NOPAppender<ILoggingEvent>)appender;
  StatusPrinter.printInCaseOfErrorsOrWarnings(loggerContext);
  statusChecker.assertContainsMatch(ErrorStatus.ERROR,"No nested appenders found");
}
