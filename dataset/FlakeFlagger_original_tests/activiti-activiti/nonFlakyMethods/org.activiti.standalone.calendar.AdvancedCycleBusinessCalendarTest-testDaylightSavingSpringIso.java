public void testDaylightSavingSpringIso() throws Exception {
  AdvancedCycleBusinessCalendar businessCalendar=new AdvancedCycleBusinessCalendar(testingClock);
  testingClock.setCurrentCalendar(parseCalendar("20140309-05:00:00",TimeZone.getTimeZone("UTC")));
  assertEquals(parseCalendar("20140310-04:00:00",TimeZone.getTimeZone("UTC")).getTime(),businessCalendar.resolveDuedate("R2/2014-03-09T00:00:00-05:00/P1D DSTZONE:US/Eastern"));
}
