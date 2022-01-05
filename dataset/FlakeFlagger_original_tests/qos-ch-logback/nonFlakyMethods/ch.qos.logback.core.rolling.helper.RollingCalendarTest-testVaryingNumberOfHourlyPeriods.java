public void testVaryingNumberOfHourlyPeriods(){
  RollingCalendar rc=new RollingCalendar();
  rc.init("yyyy-MM-dd_HH");
  long MILLIS_IN_HOUR=3600 * 1000;
  for (int p=100; p > -100; p--) {
    long now=1223325293589L;
    Date result=rc.getRelativeDate(new Date(now),p);
    long expected=now - (now % (MILLIS_IN_HOUR)) + p * MILLIS_IN_HOUR;
    assertEquals(expected,result.getTime());
  }
}
