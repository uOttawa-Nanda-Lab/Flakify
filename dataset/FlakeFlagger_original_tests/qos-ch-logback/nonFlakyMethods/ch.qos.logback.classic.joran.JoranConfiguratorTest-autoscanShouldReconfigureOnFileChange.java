@Test public void autoscanShouldReconfigureOnFileChange() throws Exception {
  String configFileAsStr=ClassicTestConstants.JORAN_INPUT_PREFIX + "scan1.xml";
  configure(configFileAsStr);
  File file=new File(configFileAsStr);
  file.setLastModified(System.currentTimeMillis());
  Thread.sleep(10);
  for (int i=0; i < 16; i++) {
    logger.debug("after " + i);
  }
  loggerContext.getExecutorService().shutdown();
  loggerContext.getExecutorService().awaitTermination(1000,TimeUnit.MILLISECONDS);
  StatusChecker checker=new StatusChecker(loggerContext);
  checker.assertIsErrorFree();
  checker.assertContainsMatch(CoreConstants.RESET_MSG_PREFIX);
}
