public void testLegacyIsoString() throws Exception {
  AdvancedCycleBusinessCalendar businessCalendar=new AdvancedCycleBusinessCalendar(testingClock);
  testingClock.setCurrentCalendar(parseCalendar("20140310-04:00:00",TimeZone.getDefault()));
  assertEquals(parseCalendar("20140311-00:00:00",TimeZone.getDefault()).getTime(),businessCalendar.resolveDuedate("R2/2014-03-10T00:00:00/P1D"));
}
