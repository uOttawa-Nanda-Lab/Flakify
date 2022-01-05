public void testSimpleDuration() throws Exception {
  Clock testingClock=new DefaultClockImpl();
  CycleBusinessCalendar businessCalendar=new CycleBusinessCalendar(testingClock);
  SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy MM dd - HH:mm");
  Date now=simpleDateFormat.parse("2010 06 11 - 17:23");
  testingClock.setCurrentTime(now);
  Date duedate=businessCalendar.resolveDuedate("R/P2DT5H70M");
  Date expectedDuedate=simpleDateFormat.parse("2010 06 13 - 23:33");
  assertEquals(expectedDuedate,duedate);
}
