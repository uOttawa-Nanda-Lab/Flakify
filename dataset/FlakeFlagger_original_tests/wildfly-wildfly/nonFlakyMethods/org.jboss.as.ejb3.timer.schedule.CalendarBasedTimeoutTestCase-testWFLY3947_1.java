/** 
 * Testcase #1 for WFLY-3947
 */
@Test public void testWFLY3947_1(){
  TimeZone timeZone=TimeZone.getTimeZone("Europe/Lisbon");
  int year=2013;
  int month=Calendar.MARCH;
  int dayOfMonth=31;
  int hourOfDay=3;
  int minute=30;
  int second=0;
  Calendar start=new GregorianCalendar(timeZone);
  start.clear();
  start.set(year,month,dayOfMonth,hourOfDay,minute,second);
  ScheduleExpression expression=new ScheduleExpression().timezone(timeZone.getID()).dayOfMonth("*").hour("1").minute("30").second("0").start(start.getTime());
  CalendarBasedTimeout calendarTimeout=new CalendarBasedTimeout(expression);
  Calendar firstTimeout=calendarTimeout.getFirstTimeout();
  Assert.assertNotNull(firstTimeout);
  Assert.assertEquals(year,firstTimeout.get(Calendar.YEAR));
  Assert.assertEquals(Calendar.APRIL,firstTimeout.get(Calendar.MONTH));
  Assert.assertEquals(1,firstTimeout.get(Calendar.DAY_OF_MONTH));
  Assert.assertEquals(1,firstTimeout.get(Calendar.HOUR_OF_DAY));
  Assert.assertEquals(30,firstTimeout.get(Calendar.MINUTE));
  Assert.assertEquals(second,firstTimeout.get(Calendar.SECOND));
}
