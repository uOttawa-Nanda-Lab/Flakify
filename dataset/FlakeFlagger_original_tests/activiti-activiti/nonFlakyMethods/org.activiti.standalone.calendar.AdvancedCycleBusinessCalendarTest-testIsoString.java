public void testIsoString() throws Exception {
  AdvancedCycleBusinessCalendar businessCalendar=new AdvancedCycleBusinessCalendar(testingClock);
  testingClock.setCurrentCalendar(parseCalendar("20140310-04:00:00",TimeZone.getTimeZone("UTC")));
  assertEquals(parseCalendar("20140311-04:00:00",TimeZone.getTimeZone("UTC")).getTime(),businessCalendar.resolveDuedate("R2/2014-03-10T04:00:00/P1D DSTZONE:US/Eastern"));
}
