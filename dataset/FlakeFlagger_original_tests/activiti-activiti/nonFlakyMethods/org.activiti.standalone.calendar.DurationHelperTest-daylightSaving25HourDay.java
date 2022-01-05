@Test public void daylightSaving25HourDay() throws Exception {
  Clock testingClock=new DefaultClockImpl();
  testingClock.setCurrentCalendar(parseCalendar("20131103-00:00:00",TimeZone.getTimeZone("US/Eastern")));
  DurationHelper dh=new DurationHelper("R2/2013-11-03T00:00:00/P1D",testingClock);
  assertEquals(parseCalendar("20131104-00:00:00",TimeZone.getTimeZone("US/Eastern")),dh.getCalendarAfter(testingClock.getCurrentCalendar()));
}
