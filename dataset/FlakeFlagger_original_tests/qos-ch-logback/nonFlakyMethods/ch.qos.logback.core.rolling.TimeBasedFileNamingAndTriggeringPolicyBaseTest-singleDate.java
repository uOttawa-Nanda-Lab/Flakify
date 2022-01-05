@Test public void singleDate(){
  long startTime=1324400341553L;
  tbrp.setFileNamePattern("foo-%d{yyyy-MM'T'mm}.log");
  tbrp.start();
  timeBasedFNATP.setCurrentTime(startTime);
  timeBasedFNATP.start();
  timeBasedFNATP.setCurrentTime(startTime + MILLIS_IN_MINUTE);
  timeBasedFNATP.isTriggeringEvent(null,null);
  String elapsedPeriodsFileName=timeBasedFNATP.getElapsedPeriodsFileName();
  assertEquals("foo-2011-12T59.log",elapsedPeriodsFileName);
}
