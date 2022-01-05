@Test public void daylightSaving25HourDayEurope() throws Exception {
  Clock testingClock=new DefaultClockImpl();
  testingClock.setCurrentCalendar(parseCalendar("20131027-00:00:00",TimeZone.getTimeZone("Europe/Amsterdam")));
  DurationHelper dh=new DurationHelper("R2/2013-10-27T00:00:00/P1D",testingClock);
  assertEquals(parseCalendar("20131028-00:00:00",TimeZone.getTimeZone("Europe/Amsterdam")),dh.getCalendarAfter(testingClock.getCurrentCalendar()));
}
