/** 
 * Check if the hour/minute/second is reseted correct if the day must be updated
 */
@Test public void testDayOverflow(){
  int year=2016;
  int month=Calendar.JANUARY;
  int dayOfMonth=14;
  int hourOfDay=9;
  int minute=56;
  int second=0;
  Calendar start=new GregorianCalendar();
  start.clear();
  start.set(year,month,dayOfMonth,hourOfDay,minute,second);
  ScheduleExpression expression=new ScheduleExpression().dayOfMonth("2-13").hour("3-9").minute("0/5").second("0").start(start.getTime());
  CalendarBasedTimeout calendarTimeout=new CalendarBasedTimeout(expression);
  Calendar firstTimeout=calendarTimeout.getFirstTimeout();
  Assert.assertNotNull(firstTimeout);
  Assert.assertEquals(year,firstTimeout.get(Calendar.YEAR));
  Assert.assertEquals(1,firstTimeout.get(Calendar.MONTH));
  Assert.assertEquals(2,firstTimeout.get(Calendar.DAY_OF_MONTH));
  Assert.assertEquals(3,firstTimeout.get(Calendar.HOUR_OF_DAY));
  Assert.assertEquals(0,firstTimeout.get(Calendar.MINUTE));
  Assert.assertEquals(0,firstTimeout.get(Calendar.SECOND));
}
