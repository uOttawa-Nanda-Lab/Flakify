@Test public void dailySizeBasedRollover(){
  SizeAndTimeBasedFNATP<Object> sizeAndTimeBasedFNATP=new SizeAndTimeBasedFNATP<Object>();
  sizeAndTimeBasedFNATP.setMaxFileSize("10000");
  tbfnatp=sizeAndTimeBasedFNATP;
  slashCount=computeSlashCount(DAILY_DATE_PATTERN);
  logOverMultiplePeriodsContinuously(currentTime,randomOutputDir + "/%d{" + DAILY_DATE_PATTERN+ "}-clean.%i.zip",MILLIS_IN_DAY,5,5 * 4);
  checkPatternCompliance(5 + 1 + slashCount,"\\d{4}-\\d{2}-\\d{2}-clean(\\.\\d)(.zip)?");
}
