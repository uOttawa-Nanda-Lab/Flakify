@Test public void daylightSavingFall() throws Exception {
  Clock testingClock=new DefaultClockImpl();
  testingClock.setCurrentCalendar(parseCalendar("20131103-04:45:00",TimeZone.getTimeZone("UTC")));
  DurationHelper dh=new DurationHelper("R2/2013-11-03T00:45:00-04:00/PT1H",testingClock);
  assertEquals(parseCalendar("20131103-05:45:00",TimeZone.getTimeZone("UTC")),dh.getCalendarAfter(testingClock.getCurrentCalendar(TimeZone.getTimeZone("US/Eastern"))));
  testingClock.setCurrentCalendar(parseCalendar("20131103-05:45:00",TimeZone.getTimeZone("UTC")));
  assertEquals(parseCalendar("20131103-06:45:00",TimeZone.getTimeZone("UTC")),dh.getCalendarAfter(testingClock.getCurrentCalendar(TimeZone.getTimeZone("US/Eastern"))));
}
