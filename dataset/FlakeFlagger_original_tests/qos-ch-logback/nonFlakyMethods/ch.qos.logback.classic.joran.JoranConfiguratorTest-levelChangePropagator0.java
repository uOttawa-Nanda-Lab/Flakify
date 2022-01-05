@Test public void levelChangePropagator0() throws JoranException, IOException, InterruptedException {
  String loggerName="changePropagator0" + diff;
  java.util.logging.Logger.getLogger(loggerName).setLevel(java.util.logging.Level.INFO);
  String configFileAsStr=ClassicTestConstants.JORAN_INPUT_PREFIX + "/jul/levelChangePropagator0.xml";
  configure(configFileAsStr);
  StatusChecker checker=new StatusChecker(loggerContext);
  checker.assertIsErrorFree();
  verifyJULLevel(loggerName,null);
  verifyJULLevel("a.b.c." + diff,Level.WARN);
  verifyJULLevel(Logger.ROOT_LOGGER_NAME,Level.TRACE);
}
