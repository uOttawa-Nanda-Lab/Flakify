/** 
 * This test asserts that a timer scheduled to run during the removed hour when the Daylight Savings period starts is executed. It configures a timer to be fired on March 26, 2017 at 03:30:00 in Europe/Helsinki TZ. This hour does not exist in that timezone, this test asserts the timer is fired once during this ambiguous hour.
 */
@Test public void testTimerAtAmbiguousHourEETtoEEST(){
  Calendar start=new GregorianCalendar(TimeZone.getTimeZone("Europe/Helsinki"));
  start.clear();
  start.set(2017,Calendar.MARCH,26,0,0,0);
  ScheduleExpression schedule=new ScheduleExpression();
  schedule.hour("3").minute("30").second("0").timezone("Europe/Helsinki").start(start.getTime());
  CalendarBasedTimeout calendarTimeout=new CalendarBasedTimeout(schedule);
  Calendar timeout=calendarTimeout.getFirstTimeout();
  Assert.assertNotNull(timeout);
  if (timeout.get(Calendar.YEAR) != 2017 || timeout.get(Calendar.MONTH) != Calendar.MARCH || timeout.get(Calendar.DAY_OF_MONTH) != 26 || timeout.get(Calendar.HOUR_OF_DAY) != 3 || timeout.get(Calendar.MINUTE) != 30 || timeout.get(Calendar.SECOND) != 0 || timeout.get(Calendar.DST_OFFSET) != 0) {
    Assert.fail("Time unexpected : " + timeout.toString());
  }
  Assert.assertTrue("Schedule is more than 3h 30min hours from " + start.getTime() + " to "+ timeout.getTime(),timeout.getTimeInMillis() - start.getTimeInMillis() == 3 * 60 * 60* 1000 + 30 * 60 * 1000);
  timeout=calendarTimeout.getNextTimeout(timeout);
  if (timeout.get(Calendar.YEAR) != 2017 || timeout.get(Calendar.MONTH) != Calendar.MARCH || timeout.get(Calendar.DAY_OF_MONTH) != 27 || timeout.get(Calendar.HOUR_OF_DAY) != 3 || timeout.get(Calendar.MINUTE) != 30 || timeout.get(Calendar.SECOND) != 0 || timeout.get(Calendar.DST_OFFSET) != 3600000) {
    Assert.fail("Time unexpected : " + timeout.toString());
  }
}
