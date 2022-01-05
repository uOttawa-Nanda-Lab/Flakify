@Test public void cleanHistoryOnStart(){
  long now=this.currentTime;
  String fileNamePattern=randomOutputDir + "clean-%d{" + DAILY_DATE_PATTERN+ "}.txt";
  int maxHistory=3;
  for (int i=0; i <= 5; i++) {
    logOncePeriod(now,fileNamePattern,maxHistory);
    now=now + MILLIS_IN_DAY;
  }
  StatusPrinter.print(context);
  check(expectedCountWithoutFolders(maxHistory));
}
