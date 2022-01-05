@Test public void dailyRolloverWithSecondPhase(){
  slashCount=computeSlashCount(DAILY_DATE_PATTERN);
  int maxHistory=5;
  long endTime=logOverMultiplePeriodsContinuously(currentTime,randomOutputDir + "clean-%d{" + DAILY_DATE_PATTERN+ "}.txt",MILLIS_IN_DAY,maxHistory,maxHistory * 2);
  logOverMultiplePeriodsContinuously(endTime + MILLIS_IN_DAY * 10,randomOutputDir + "clean-%d{" + DAILY_DATE_PATTERN+ "}.txt",MILLIS_IN_DAY,maxHistory,maxHistory);
  check(expectedCountWithoutFolders(maxHistory));
}
