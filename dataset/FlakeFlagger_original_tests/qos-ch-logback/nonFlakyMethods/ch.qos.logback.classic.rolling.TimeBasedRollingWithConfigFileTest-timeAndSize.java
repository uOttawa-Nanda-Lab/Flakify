@Test public void timeAndSize() throws Exception {
  String testId="timeAndSize";
  lc.putProperty("testId",testId);
  String prefix="Hello-----";
  int approxWritesPerPeriod=64;
  sizeThreshold=prefix.length() * approxWritesPerPeriod;
  lc.putProperty("sizeThreshold","" + sizeThreshold);
  loadConfig(ClassicTestConstants.JORAN_INPUT_PREFIX + "rolling/" + testId+ ".xml");
  Logger root=lc.getLogger(Logger.ROOT_LOGGER_NAME);
  expectedFilenameList.add(randomOutputDir + "z" + testId);
  RollingFileAppender<ILoggingEvent> rfa=(RollingFileAppender<ILoggingEvent>)root.getAppender("ROLLING");
  statusChecker.assertIsErrorFree();
  TimeBasedRollingPolicy tprp=(TimeBasedRollingPolicy<ILoggingEvent>)rfa.getTriggeringPolicy();
  TimeBasedFileNamingAndTriggeringPolicy tbnatp=tprp.getTimeBasedFileNamingAndTriggeringPolicy();
  int timeIncrement=1000 / approxWritesPerPeriod;
  int runLength=approxWritesPerPeriod * 3;
  for (int i=0; i < runLength; i++) {
    String msg=prefix + i;
    logger.debug(msg);
    addExpectedFileNamedIfItsTime(testId,msg,false);
    incCurrentTime(timeIncrement);
    tbnatp.setCurrentTime(currentTime);
  }
  sortedContentCheck(randomOutputDir,runLength,prefix);
  int eCount=existenceCount(expectedFilenameList);
  assertTrue("exitenceCount=" + eCount + ", expectedFilenameList.size="+ expectedFilenameList.size(),eCount >= 4 && eCount > expectedFilenameList.size() / 2);
}
