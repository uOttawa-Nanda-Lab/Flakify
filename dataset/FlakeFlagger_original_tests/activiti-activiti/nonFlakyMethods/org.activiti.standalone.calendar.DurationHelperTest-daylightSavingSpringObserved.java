@Test public void daylightSavingSpringObserved() throws Exception {
  Clock testingClock=new DefaultClockImpl();
  testingClock.setCurrentCalendar(parseCalendar("20140309-01:45:00",TimeZone.getTimeZone("US/Eastern")));
  DurationHelper dh=new DurationHelper("R2/2014-03-09T01:45:00/PT1H",testingClock);
  Calendar expected=parseCalendar("20140309-03:45:00",TimeZone.getTimeZone("US/Eastern"));
  assertEquals(expected,dh.getCalendarAfter());
}
