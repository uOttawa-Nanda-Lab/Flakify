public void testVaryingNumberOfDailyPeriods(){
  RollingCalendar rc=new RollingCalendar();
  rc.init("yyyy-MM-dd");
  final long MILLIS_IN_DAY=24 * 3600 * 1000;
  for (int p=20; p > -100; p--) {
    long now=1223325293589L;
    Date nowDate=new Date(now);
    Date result=rc.getRelativeDate(nowDate,p);
    long offset=rc.getTimeZone().getRawOffset() + rc.getTimeZone().getDSTSavings();
    long origin=now - (now % (MILLIS_IN_DAY)) - offset;
    long expected=origin + p * MILLIS_IN_DAY;
    assertEquals("p=" + p,expected,result.getTime());
  }
}
