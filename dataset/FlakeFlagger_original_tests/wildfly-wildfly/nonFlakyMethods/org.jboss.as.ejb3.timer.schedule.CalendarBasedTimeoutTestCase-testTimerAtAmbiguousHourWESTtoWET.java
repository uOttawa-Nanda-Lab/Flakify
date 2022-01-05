/** 
 * This test asserts that a timer scheduled to run during the ambiguous hour when the Daylight Savings period ends is not executed twice. It configures a timer to be fired on October 29, 2017 at 01:30:00 in Europe/Lisbon TZ. There are two 01:30:00 that day: 01:30:00 WEST and 01:30:00 WET. The timer has to be fired just once.
 */
@Test public void testTimerAtAmbiguousHourWESTtoWET(){
  Calendar start=new GregorianCalendar(TimeZone.getTimeZone("Europe/Lisbon"));
  start.clear();
  start.set(2017,Calendar.OCTOBER,29,0,0,0);
  ScheduleExpression schedule=new ScheduleExpression();
  schedule.hour("1").minute("30").second("0").timezone("Europe/Lisbon").start(start.getTime());
  CalendarBasedTimeout calendarTimeout=new CalendarBasedTimeout(schedule);
  Calendar timeout=calendarTimeout.getFirstTimeout();
  Assert.assertNotNull(timeout);
  if (timeout.get(Calendar.YEAR) != 2017 || timeout.get(Calendar.MONTH) != Calendar.OCTOBER || timeout.get(Calendar.DAY_OF_MONTH) != 29 || timeout.get(Calendar.HOUR_OF_DAY) != 1 || timeout.get(Calendar.MINUTE) != 30 || timeout.get(Calendar.SECOND) != 0 || timeout.get(Calendar.DST_OFFSET) != 3600000) {
    Assert.fail("Time unexpected : " + timeout.toString());
  }
  Assert.assertTrue("Schedule is more than 1h 30min hours from " + start.getTime() + " to "+ timeout.getTime(),timeout.getTimeInMillis() - start.getTimeInMillis() == 1 * 60 * 60* 1000 + 30 * 60 * 1000);
  timeout=calendarTimeout.getNextTimeout(timeout);
  if (timeout.get(Calendar.YEAR) != 2017 || timeout.get(Calendar.MONTH) != Calendar.OCTOBER || timeout.get(Calendar.DAY_OF_MONTH) != 30 || timeout.get(Calendar.HOUR_OF_DAY) != 1 || timeout.get(Calendar.MINUTE) != 30 || timeout.get(Calendar.SECOND) != 0 || timeout.get(Calendar.DST_OFFSET) != 0) {
    Assert.fail("Time unexpected : " + timeout.toString());
  }
}
