public void testDaylightSavingFallIso() throws Exception {
  AdvancedCycleBusinessCalendar businessCalendar=new AdvancedCycleBusinessCalendar(testingClock);
  testingClock.setCurrentCalendar(parseCalendar("20131103-04:00:00",TimeZone.getTimeZone("UTC")));
  assertEquals(parseCalendar("20131104-05:00:00",TimeZone.getTimeZone("UTC")).getTime(),businessCalendar.resolveDuedate("R2/2013-11-03T00:00:00-04:00/P1D DSTZONE:US/Eastern"));
}
