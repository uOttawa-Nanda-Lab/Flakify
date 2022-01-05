@Test public void daylightSavingFallObservedFirstHour() throws Exception {
  Clock testingClock=new DefaultClockImpl();
  testingClock.setCurrentCalendar(parseCalendar("20131103-00:45:00",TimeZone.getTimeZone("US/Eastern")));
  DurationHelper dh=new DurationHelper("R2/2013-11-03T00:45:00-04:00/PT1H",testingClock);
  Calendar expected=parseCalendarWithOffset("20131103-01:45:00 -04:00");
  assertTrue(expected.compareTo(dh.getCalendarAfter()) == 0);
}
