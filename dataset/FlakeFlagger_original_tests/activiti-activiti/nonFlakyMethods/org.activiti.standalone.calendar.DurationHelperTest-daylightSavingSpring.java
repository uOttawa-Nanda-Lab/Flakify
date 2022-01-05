@Test public void daylightSavingSpring() throws Exception {
  Clock testingClock=new DefaultClockImpl();
  testingClock.setCurrentCalendar(parseCalendar("20140309-05:45:00",TimeZone.getTimeZone("UTC")));
  DurationHelper dh=new DurationHelper("R2/2014-03-09T00:45:00-05:00/PT1H",testingClock);
  assertEquals(parseCalendar("20140309-06:45:00",TimeZone.getTimeZone("UTC")),dh.getCalendarAfter(testingClock.getCurrentCalendar(TimeZone.getTimeZone("US/Eastern"))));
}
