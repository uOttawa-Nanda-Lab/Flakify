@Test public void testWeek(){
  long now=1164045917522L;
  now=correctBasedOnTimeZone(now);
  long expected=1164499200000L;
  expected=correctBasedOnTimeZone(expected);
  Calendar cal=Calendar.getInstance();
  cal.setTime(new Date(now));
  int dayOffset=cal.getFirstDayOfWeek() - Calendar.SUNDAY;
  if (dayOffset != 0) {
    expected+=24L * 3600 * 1000* (cal.getFirstDayOfWeek() - Calendar.SUNDAY);
  }
  long computed=TimeUtil.computeStartOfNextWeek(now);
  assertEquals(expected - now,1000 * (3600 * (5 + 24 * (5 + dayOffset)) + 60 * 54 + 42) + 478);
  assertEquals(expected,computed);
}
