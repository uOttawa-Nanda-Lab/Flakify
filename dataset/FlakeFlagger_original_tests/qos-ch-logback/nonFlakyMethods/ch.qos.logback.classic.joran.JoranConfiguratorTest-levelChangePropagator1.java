@Test public void levelChangePropagator1() throws JoranException, IOException, InterruptedException {
  String loggerName="changePropagator1" + diff;
  java.util.logging.Logger.getLogger(loggerName).setLevel(java.util.logging.Level.INFO);
  verifyJULLevel(loggerName,Level.INFO);
  String configFileAsStr=ClassicTestConstants.JORAN_INPUT_PREFIX + "/jul/levelChangePropagator1.xml";
  configure(configFileAsStr);
  StatusChecker checker=new StatusChecker(loggerContext);
  checker.assertIsErrorFree();
  verifyJULLevel(loggerName,Level.INFO);
  verifyJULLevel("a.b.c." + diff,Level.WARN);
  verifyJULLevel(Logger.ROOT_LOGGER_NAME,Level.TRACE);
}
