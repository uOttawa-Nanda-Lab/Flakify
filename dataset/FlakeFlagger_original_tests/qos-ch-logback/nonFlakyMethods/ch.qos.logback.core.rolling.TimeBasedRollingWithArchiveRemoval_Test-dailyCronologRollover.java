@Test public void dailyCronologRollover(){
  slashCount=computeSlashCount(DAILY_CRONOLOG_DATE_PATTERN);
  logOverMultiplePeriodsContinuously(currentTime,randomOutputDir + "/%d{" + DAILY_CRONOLOG_DATE_PATTERN+ "}/clean.txt.zip",MILLIS_IN_DAY,8,8 * 3);
  int expectedDirMin=9 + slashCount;
  int expectDirMax=expectedDirMin + 1 + 1;
  expectedFileAndDirCount(9,expectedDirMin,expectDirMax);
}
