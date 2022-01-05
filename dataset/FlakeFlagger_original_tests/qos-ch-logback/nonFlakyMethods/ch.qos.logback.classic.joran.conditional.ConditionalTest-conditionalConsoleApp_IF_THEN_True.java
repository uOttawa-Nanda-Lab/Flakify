@SuppressWarnings("rawtypes") @Test public void conditionalConsoleApp_IF_THEN_True() throws JoranException, IOException, InterruptedException {
  InetAddress localhost=InetAddress.getLocalHost();
  System.out.println("In conditionalConsoleApp_IF_THEN_True, canonicalHostName=\"" + localhost.getCanonicalHostName() + "] and hostNmae=\""+ localhost.getHostName()+ "\"");
  context.putProperty("aHost",localhost.getHostName());
  String configFileAsStr=ClassicTestConstants.JORAN_INPUT_PREFIX + "conditional/conditionalConsoleApp.xml";
  configure(configFileAsStr);
  FileAppender fileAppender=(FileAppender)root.getAppender("FILE");
  assertNotNull(fileAppender);
  ConsoleAppender consoleAppender=(ConsoleAppender)root.getAppender("CON");
  assertNotNull(consoleAppender);
  StatusChecker checker=new StatusChecker(context);
  checker.assertIsErrorFree();
}
