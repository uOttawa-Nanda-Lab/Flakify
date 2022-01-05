@Test public void encoderCharset() throws JoranException, IOException, InterruptedException {
  String configFileAsStr=ClassicTestConstants.JORAN_INPUT_PREFIX + "encoderCharset.xml";
  configure(configFileAsStr);
  ConsoleAppender<ILoggingEvent> consoleAppender=(ConsoleAppender<ILoggingEvent>)root.getAppender("CONSOLE");
  assertNotNull(consoleAppender);
  LayoutWrappingEncoder<ILoggingEvent> encoder=(LayoutWrappingEncoder<ILoggingEvent>)consoleAppender.getEncoder();
  assertEquals("UTF-8",encoder.getCharset().displayName());
  StatusChecker checker=new StatusChecker(loggerContext);
  checker.assertIsErrorFree();
}
