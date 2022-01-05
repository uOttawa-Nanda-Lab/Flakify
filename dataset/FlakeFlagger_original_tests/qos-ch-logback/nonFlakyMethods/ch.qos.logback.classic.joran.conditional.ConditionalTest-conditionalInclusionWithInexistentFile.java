@Test public void conditionalInclusionWithInexistentFile() throws JoranException, IOException, InterruptedException {
  String configFileAsStr=ClassicTestConstants.JORAN_INPUT_PREFIX + "conditional/conditionalIncludeInexistentFile.xml";
  configure(configFileAsStr);
  ConsoleAppender consoleAppender=(ConsoleAppender)root.getAppender("CON");
  assertNull(consoleAppender);
  StatusChecker checker=new StatusChecker(context);
  checker.assertIsErrorFree();
}
