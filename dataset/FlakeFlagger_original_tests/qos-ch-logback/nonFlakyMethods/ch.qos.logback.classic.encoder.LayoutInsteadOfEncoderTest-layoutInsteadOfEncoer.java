@Test public void layoutInsteadOfEncoer() throws JoranException {
  jc.doConfigure(ClassicTestConstants.JORAN_INPUT_PREFIX + "compatibility/layoutInsteadOfEncoder.xml");
  StatusPrinter.print(loggerContext);
  StatusChecker checker=new StatusChecker(loggerContext);
  checker.assertContainsMatch(Status.WARN,"This appender no longer admits a layout as a sub-component");
  checker.assertContainsMatch(Status.WARN,"See also " + CODES_URL + "#layoutInsteadOfEncoder for details");
  ch.qos.logback.classic.Logger root=(ch.qos.logback.classic.Logger)loggerContext.getLogger(Logger.ROOT_LOGGER_NAME);
  FileAppender<ILoggingEvent> fileAppender=(FileAppender<ILoggingEvent>)root.getAppender("LIOE");
  assertTrue(fileAppender.isStarted());
  assertTrue(fileAppender.getEncoder() instanceof LayoutWrappingEncoder);
}
