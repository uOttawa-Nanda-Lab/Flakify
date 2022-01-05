/** 
 * If we have an overflow for minutes, the seconds must be reseted. Test for WFLY-5995
 */
@Test public void testWFLY5995_MinuteOverflow(){
  int year=2016;
  int month=Calendar.JANUARY;
  int dayOfMonth=14;
  int hourOfDay=9;
  int minute=46;
  int second=42;
  Calendar start=new GregorianCalendar();
  start.clear();
  start.set(year,month,dayOfMonth,hourOfDay,minute,second);
  ScheduleExpression expression=new ScheduleExpression().dayOfMonth("*").hour("*").minute("0-45").second("0/10").start(start.getTime());
  CalendarBasedTimeout calendarTimeout=new CalendarBasedTimeout(expression);
  Calendar firstTimeout=calendarTimeout.getFirstTimeout();
  Assert.assertNotNull(firstTimeout);
  Assert.assertEquals(year,firstTimeout.get(Calendar.YEAR));
  Assert.assertEquals(month,firstTimeout.get(Calendar.MONTH));
  Assert.assertEquals(dayOfMonth,firstTimeout.get(Calendar.DAY_OF_MONTH));
  Assert.assertEquals(10,firstTimeout.get(Calendar.HOUR_OF_DAY));
  Assert.assertEquals(0,firstTimeout.get(Calendar.MINUTE));
  Assert.assertEquals(0,firstTimeout.get(Calendar.SECOND));
}
