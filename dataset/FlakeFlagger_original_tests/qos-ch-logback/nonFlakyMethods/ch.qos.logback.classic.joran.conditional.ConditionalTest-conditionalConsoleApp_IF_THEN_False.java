@SuppressWarnings("rawtypes") @Test public void conditionalConsoleApp_IF_THEN_False() throws JoranException, IOException, InterruptedException {
  String configFileAsStr=ClassicTestConstants.JORAN_INPUT_PREFIX + "conditional/conditionalConsoleApp.xml";
  configure(configFileAsStr);
  FileAppender fileAppender=(FileAppender)root.getAppender("FILE");
  assertNotNull(fileAppender);
  ConsoleAppender consoleAppender=(ConsoleAppender)root.getAppender("CON");
  assertNull(consoleAppender);
  StatusChecker checker=new StatusChecker(context);
  checker.assertIsErrorFree();
}
