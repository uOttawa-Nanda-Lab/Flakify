/** 
 * Testcase #2 for WFLY-3947
 */
@Test public void testWFLY3947_2(){
  TimeZone timeZone=TimeZone.getTimeZone("Australia/Lord_Howe");
  int year=2013;
  int month=Calendar.OCTOBER;
  int dayOfMonth=6;
  int hourOfDay=2;
  int minute=41;
  int second=0;
  Calendar start=new GregorianCalendar(timeZone);
  start.clear();
  start.set(year,month,dayOfMonth,hourOfDay,minute,second);
  ScheduleExpression expression=new ScheduleExpression().timezone(timeZone.getID()).dayOfMonth("*").hour("2, 3").minute("20, 40").second("0").start(start.getTime());
  CalendarBasedTimeout calendarTimeout=new CalendarBasedTimeout(expression);
  Calendar firstTimeout=calendarTimeout.getFirstTimeout();
  Assert.assertNotNull(firstTimeout);
  Assert.assertEquals(year,firstTimeout.get(Calendar.YEAR));
  Assert.assertEquals(month,firstTimeout.get(Calendar.MONTH));
  Assert.assertEquals(dayOfMonth,firstTimeout.get(Calendar.DAY_OF_MONTH));
  Assert.assertEquals(3,firstTimeout.get(Calendar.HOUR_OF_DAY));
  Assert.assertEquals(20,firstTimeout.get(Calendar.MINUTE));
  Assert.assertEquals(second,firstTimeout.get(Calendar.SECOND));
}
