@Test public void basic() throws Exception {
  String testId="basic";
  lc.putProperty("testId",testId);
  loadConfig(ClassicTestConstants.JORAN_INPUT_PREFIX + "rolling/" + testId+ ".xml");
  statusChecker.assertIsErrorFree();
  Logger root=lc.getLogger(Logger.ROOT_LOGGER_NAME);
  expectedFilenameList.add(randomOutputDir + "z" + testId);
  RollingFileAppender<ILoggingEvent> rfa=(RollingFileAppender<ILoggingEvent>)root.getAppender("ROLLING");
  TimeBasedRollingPolicy tprp=(TimeBasedRollingPolicy<ILoggingEvent>)rfa.getTriggeringPolicy();
  TimeBasedFileNamingAndTriggeringPolicy tbnatp=tprp.getTimeBasedFileNamingAndTriggeringPolicy();
  String prefix="Hello---";
  int runLength=4;
  for (int i=0; i < runLength; i++) {
    logger.debug(prefix + i);
    addExpectedFileNamedIfItsTime_ByDate(randomOutputDir,testId,false);
    incCurrentTime(500);
    tbnatp.setCurrentTime(currentTime);
  }
  existenceCheck(expectedFilenameList);
  sortedContentCheck(randomOutputDir,runLength,prefix);
}
