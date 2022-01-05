@Test public void daylightSaving23HourDay() throws Exception {
  Clock testingClock=new DefaultClockImpl();
  testingClock.setCurrentCalendar(parseCalendar("20140309-00:00:00",TimeZone.getTimeZone("US/Eastern")));
  DurationHelper dh=new DurationHelper("R2/2014-03-09T00:00:00/P1D",testingClock);
  assertEquals(parseCalendar("20140310-00:00:00",TimeZone.getTimeZone("US/Eastern")),dh.getCalendarAfter(testingClock.getCurrentCalendar()));
}
