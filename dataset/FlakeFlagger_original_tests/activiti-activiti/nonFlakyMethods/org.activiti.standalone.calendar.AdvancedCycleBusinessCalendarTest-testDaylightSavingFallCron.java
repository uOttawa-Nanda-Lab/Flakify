public void testDaylightSavingFallCron() throws Exception {
  AdvancedCycleBusinessCalendar businessCalendar=new AdvancedCycleBusinessCalendar(testingClock);
  testingClock.setCurrentCalendar(parseCalendar("20131103-04:00:00",TimeZone.getTimeZone("UTC")));
  assertEquals(parseCalendar("20131103-17:00:00",TimeZone.getTimeZone("UTC")).getTime(),businessCalendar.resolveDuedate("0 0 12 1/1 * ? * DSTZONE:US/Eastern"));
}
