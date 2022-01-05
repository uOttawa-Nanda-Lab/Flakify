public void testDaylightSavingSpringCron() throws Exception {
  AdvancedCycleBusinessCalendar businessCalendar=new AdvancedCycleBusinessCalendar(testingClock);
  testingClock.setCurrentCalendar(parseCalendar("20140309-05:00:00",TimeZone.getTimeZone("UTC")));
  assertEquals(parseCalendar("20140309-16:00:00",TimeZone.getTimeZone("UTC")).getTime(),businessCalendar.resolveDuedate("0 0 12 1/1 * ? * DSTZONE:US/Eastern"));
}
