@Test public void dailyChronologSizeBasedRolloverWithSecondPhase(){
  SizeAndTimeBasedFNATP<Object> sizeAndTimeBasedFNATP=new SizeAndTimeBasedFNATP<Object>();
  sizeAndTimeBasedFNATP.setMaxFileSize("10000");
  tbfnatp=sizeAndTimeBasedFNATP;
  slashCount=1;
  int maxHistory=5;
  int simulatedNumberOfPeriods=maxHistory * 4;
  long endTime=logOverMultiplePeriodsContinuously(currentTime,randomOutputDir + "/%d{" + DAILY_DATE_PATTERN+ "}/clean.%i",MILLIS_IN_DAY,maxHistory,3);
  logOverMultiplePeriodsContinuously(endTime + MILLIS_IN_DAY * 7,randomOutputDir + "/%d{" + DAILY_DATE_PATTERN+ "}/clean.%i",MILLIS_IN_DAY,maxHistory,simulatedNumberOfPeriods);
  checkDirPatternCompliance(maxHistory + 1);
}
