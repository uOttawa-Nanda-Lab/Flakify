@Test public void daylightSaving23HourDayEurope() throws Exception {
  Clock testingClock=new DefaultClockImpl();
  testingClock.setCurrentCalendar(parseCalendar("20140330-00:00:00",TimeZone.getTimeZone("Europe/Amsterdam")));
  DurationHelper dh=new DurationHelper("R2/2014-03-30T00:00:00/P1D",testingClock);
  assertEquals(parseCalendar("20140331-00:00:00",TimeZone.getTimeZone("Europe/Amsterdam")),dh.getCalendarAfter(testingClock.getCurrentCalendar()));
}
