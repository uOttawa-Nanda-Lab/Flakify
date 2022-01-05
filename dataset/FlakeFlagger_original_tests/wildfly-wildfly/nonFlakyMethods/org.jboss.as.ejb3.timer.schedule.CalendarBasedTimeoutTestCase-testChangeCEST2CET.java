/** 
 * Change CEST summer time to CEST winter time. The timer should be fired every 15 minutes (absolutely). The calendar time will jump from 3:00CEST back to 2:00CET but the timer must run within 2:00-3:00 CEST and 2:00-3:00CET! The test should be run similar in any OS/JVM default timezone This is a test for WFLY-9537
 */
@Test public void testChangeCEST2CET(){
  Calendar start=new GregorianCalendar(TimeZone.getTimeZone("Europe/Berlin"));
  start.clear();
  start.set(2017,Calendar.OCTOBER,29,1,30,0);
  ScheduleExpression schedule=new ScheduleExpression();
  schedule.hour("*").minute("5/15").second("0").timezone("Europe/Berlin").start(start.getTime());
  CalendarBasedTimeout calendarTimeout=new CalendarBasedTimeout(schedule);
  Calendar firstTimeout=calendarTimeout.getFirstTimeout();
  Assert.assertNotNull(firstTimeout);
  if (firstTimeout.get(Calendar.YEAR) != 2017 || firstTimeout.get(Calendar.MONTH) != Calendar.OCTOBER || firstTimeout.get(Calendar.DAY_OF_MONTH) != 29 || firstTimeout.get(Calendar.HOUR_OF_DAY) != 1 || firstTimeout.get(Calendar.MINUTE) != 35 || firstTimeout.get(Calendar.SECOND) != 0 || firstTimeout.get(Calendar.DST_OFFSET) != 3600000) {
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
  if (current.get(Calendar.YEAR) != 2017 || current.get(Calendar.MONTH) != Calendar.OCTOBER || current.get(Calendar.DAY_OF_MONTH) != 29 || current.get(Calendar.HOUR_OF_DAY) != 2 || current.get(Calendar.MINUTE) != 20 || current.get(Calendar.DST_OFFSET) != 0) {
    Assert.fail("End time unexpected : " + current.toString());
  }
}
