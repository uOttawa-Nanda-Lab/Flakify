/** 
 * Change PST summer time to PST winter time. The timer should be fired every 15 minutes (absolutely). This is a test for WFLY-9537
 */
@Test public void testChangeUS2Winter(){
  Calendar start=new GregorianCalendar(TimeZone.getTimeZone("America/Los_Angeles"));
  start.clear();
  start.set(2017,Calendar.NOVEMBER,5,0,30,0);
  ScheduleExpression schedule=new ScheduleExpression();
  schedule.hour("*").minute("0/15").second("0").timezone("America/Los_Angeles").start(start.getTime());
  CalendarBasedTimeout calendarTimeout=new CalendarBasedTimeout(schedule);
  Calendar firstTimeout=calendarTimeout.getFirstTimeout();
  Assert.assertNotNull(firstTimeout);
  if (firstTimeout.get(Calendar.YEAR) != 2017 || firstTimeout.get(Calendar.MONTH) != Calendar.NOVEMBER || firstTimeout.get(Calendar.DAY_OF_MONTH) != 5 || firstTimeout.get(Calendar.HOUR_OF_DAY) != 0 || firstTimeout.get(Calendar.MINUTE) != 30 || firstTimeout.get(Calendar.SECOND) != 0 || firstTimeout.get(Calendar.DST_OFFSET) != 3600000) {
    Assert.fail("Start time unexpected : " + firstTimeout.toString());
  }
  Calendar current=firstTimeout;
  for (int i=0; i < 7; i++) {
    Calendar next=calendarTimeout.getNextTimeout(current);
    if (current.getTimeInMillis() != (next.getTimeInMillis() - 900000)) {
      Assert.fail("Schedule is more than 15 minutes from " + current.getTime() + " to "+ next.getTime());
    }
    current=next;
  }
  if (current.get(Calendar.YEAR) != 2017 || current.get(Calendar.MONTH) != Calendar.NOVEMBER || current.get(Calendar.DAY_OF_MONTH) != 5 || current.get(Calendar.HOUR_OF_DAY) != 1 || current.get(Calendar.MINUTE) != 15 || current.get(Calendar.DST_OFFSET) != 0) {
    Assert.fail("End time unexpected : " + current.toString());
  }
}
