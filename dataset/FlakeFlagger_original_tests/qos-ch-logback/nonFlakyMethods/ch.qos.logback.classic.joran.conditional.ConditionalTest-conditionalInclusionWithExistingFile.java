@Test public void conditionalInclusionWithExistingFile() throws JoranException, IOException, InterruptedException {
  String configFileAsStr=ClassicTestConstants.JORAN_INPUT_PREFIX + "conditional/conditionalIncludeExistingFile.xml";
  configure(configFileAsStr);
  ConsoleAppender consoleAppender=(ConsoleAppender)root.getAppender("CON");
  assertNotNull(consoleAppender);
  StatusChecker checker=new StatusChecker(context);
  checker.assertIsErrorFree();
}
