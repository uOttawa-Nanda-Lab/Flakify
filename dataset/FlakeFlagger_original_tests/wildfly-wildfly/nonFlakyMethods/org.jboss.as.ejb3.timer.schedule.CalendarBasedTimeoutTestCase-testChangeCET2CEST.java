/** 
 * Change CET winter time to CEST summer time. The timer should be fired every 15 minutes (absolutely). The calendar time will jump from 2:00CET to 3:00CEST The test should be run similar in any OS/JVM default timezone This is a test to ensure WFLY-9537 will not break this.
 */
@Test public void testChangeCET2CEST(){
  Calendar start=new GregorianCalendar(TimeZone.getTimeZone("Europe/Berlin"));
  start.clear();
  start.set(2017,Calendar.MARCH,26,1,30,0);
  ScheduleExpression schedule=new ScheduleExpression();
  schedule.hour("*").minute("0/15").second("0").timezone("Europe/Berlin").start(start.getTime());
  CalendarBasedTimeout calendarTimeout=new CalendarBasedTimeout(schedule);
  Calendar firstTimeout=calendarTimeout.getFirstTimeout();
  Assert.assertNotNull(firstTimeout);
  if (firstTimeout.get(Calendar.YEAR) != 2017 || firstTimeout.get(Calendar.MONTH) != Calendar.MARCH || firstTimeout.get(Calendar.DAY_OF_MONTH) != 26 || firstTimeout.get(Calendar.HOUR_OF_DAY) != 1 || firstTimeout.get(Calendar.MINUTE) != 30 || firstTimeout.get(Calendar.SECOND) != 0 || firstTimeout.get(Calendar.DST_OFFSET) != 0) {
    Assert.fail("Start time unexpected : " + firstTimeout.toString());
  }
  Calendar current=firstTimeout;
  for (int i=0; i < 3; i++) {
    Calendar next=calendarTimeout.getNextTimeout(current);
    if (current.getTimeInMillis() != (next.getTimeInMillis() - 900000)) {
      Assert.fail("Schedule is more than 15 minutes from " + current.getTime() + " to "+ next.getTime());
    }
    current=next;
  }
  if (current.get(Calendar.YEAR) != 2017 || current.get(Calendar.MONTH) != Calendar.MARCH || current.get(Calendar.DAY_OF_MONTH) != 26 || current.get(Calendar.HOUR_OF_DAY) != 3 || current.get(Calendar.MINUTE) != 15 || current.get(Calendar.DST_OFFSET) != 3600000) {
    Assert.fail("End time unexpected : " + current.toString());
  }
}
