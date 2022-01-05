@Test public void dailyChronologSizeBasedRollover(){
  SizeAndTimeBasedFNATP<Object> sizeAndTimeBasedFNATP=new SizeAndTimeBasedFNATP<Object>();
  sizeAndTimeBasedFNATP.setMaxFileSize("10000");
  tbfnatp=sizeAndTimeBasedFNATP;
  slashCount=1;
  logOverMultiplePeriodsContinuously(currentTime,randomOutputDir + "/%d{" + DAILY_DATE_PATTERN+ "}/clean.%i.zip",MILLIS_IN_DAY,5,5 * 4);
  checkDirPatternCompliance(6);
}
