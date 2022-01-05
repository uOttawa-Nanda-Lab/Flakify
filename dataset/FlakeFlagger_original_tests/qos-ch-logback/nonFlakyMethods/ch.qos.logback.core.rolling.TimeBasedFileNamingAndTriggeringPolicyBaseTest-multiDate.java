@Test public void multiDate(){
  long startTime=1324400341553L;
  tbrp.setFileNamePattern("foo-%d{yyyy-MM, AUX}/%d{mm}.log");
  tbrp.start();
  timeBasedFNATP.setCurrentTime(startTime);
  timeBasedFNATP.start();
  timeBasedFNATP.setCurrentTime(startTime + MILLIS_IN_MINUTE);
  timeBasedFNATP.isTriggeringEvent(null,null);
  String elapsedPeriodsFileName=timeBasedFNATP.getElapsedPeriodsFileName();
  assertEquals("foo-2011-12/59.log",elapsedPeriodsFileName);
}
