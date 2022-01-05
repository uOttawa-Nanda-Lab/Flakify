public void testSimpleCron() throws Exception {
  Clock testingClock=new DefaultClockImpl();
  CycleBusinessCalendar businessCalendar=new CycleBusinessCalendar(testingClock);
  SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy MM dd - HH:mm");
  Date now=simpleDateFormat.parse("2011 03 11 - 17:23");
  testingClock.setCurrentTime(now);
  Date duedate=businessCalendar.resolveDuedate("0 0 0 1 * ?");
  Date expectedDuedate=simpleDateFormat.parse("2011 04 1 - 00:00");
  assertEquals(expectedDuedate,duedate);
}
