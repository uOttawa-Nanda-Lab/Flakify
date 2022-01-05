@Test public void multipleNesting() throws JoranException {
  configure(SIFT_FOLDER_PREFIX + "multipleNesting.xml");
  logger.debug("hello");
  logger.debug("hello");
  logger.debug("hello");
  Appender<ILoggingEvent> listAppender=getAppenderTracker().find("multipleDefault");
  StatusPrinter.printInCaseOfErrorsOrWarnings(loggerContext);
  assertNotNull(listAppender);
  statusChecker.assertContainsMatch(ErrorStatus.ERROR,"Only and only one appender can be nested");
}
