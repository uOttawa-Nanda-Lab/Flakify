@Test public void monthlyRolloverOverManyPeriods(){
  slashCount=computeSlashCount(MONTHLY_CRONOLOG_DATE_PATTERN);
  int numPeriods=40;
  int maxHistory=2;
  String fileNamePattern=randomOutputDir + "/%d{" + MONTHLY_CRONOLOG_DATE_PATTERN+ "}/clean.txt.zip";
  long startTime=currentTime;
  long endTime=logOverMultiplePeriodsContinuously(currentTime,fileNamePattern,MILLIS_IN_MONTH,maxHistory,numPeriods);
  System.out.println("randomOutputDir:" + randomOutputDir);
  System.out.println("start:" + startTime + ", end="+ endTime);
  int differenceInMonths=RollingCalendar.diffInMonths(startTime,endTime);
  System.out.println("differenceInMonths:" + differenceInMonths);
  Calendar startTimeAsCalendar=Calendar.getInstance();
  startTimeAsCalendar.setTimeInMillis(startTime);
  int indexOfStartPeriod=startTimeAsCalendar.get(Calendar.MONTH);
  boolean withExtraFolder=extraFolder(differenceInMonths,MONTHS_IN_YEAR,indexOfStartPeriod,maxHistory);
  check(expectedCountWithFolders(maxHistory,withExtraFolder));
}
