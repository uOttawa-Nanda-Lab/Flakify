/** 
 * Tests that the  {@link CalendarUtil#getLastDateOfMonth(java.util.Calendar)} returns the correctdate for various months.
 */
@Test public void testLastDateOfMonth(){
  Calendar march=new GregorianCalendar();
  march.set(Calendar.MONTH,Calendar.MARCH);
  march.set(Calendar.DAY_OF_MONTH,1);
  int lastDateOfMarch=CalendarUtil.getLastDateOfMonth(march);
  Assert.assertEquals("Unexpected last date for march",31,lastDateOfMarch);
  Calendar april=new GregorianCalendar();
  april.set(Calendar.MONTH,Calendar.APRIL);
  april.set(Calendar.DAY_OF_MONTH,1);
  int lastDateOfApril=CalendarUtil.getLastDateOfMonth(april);
  Assert.assertEquals("Unexpected last date for april",30,lastDateOfApril);
  Calendar nonLeapFebruary=new GregorianCalendar();
  nonLeapFebruary.set(Calendar.MONTH,Calendar.FEBRUARY);
  nonLeapFebruary.set(Calendar.YEAR,2010);
  nonLeapFebruary.set(Calendar.DAY_OF_MONTH,1);
  int lastDateOfNonLeapFebruary=CalendarUtil.getLastDateOfMonth(nonLeapFebruary);
  Assert.assertEquals("Unexpected last date for non-leap february",28,lastDateOfNonLeapFebruary);
  Calendar leapFebruary=new GregorianCalendar();
  leapFebruary.set(Calendar.MONTH,Calendar.FEBRUARY);
  leapFebruary.set(Calendar.YEAR,2012);
  leapFebruary.set(Calendar.DAY_OF_MONTH,1);
  int lastDateOfLeapFebruary=CalendarUtil.getLastDateOfMonth(leapFebruary);
  Assert.assertEquals("Unexpected last date for leap february",29,lastDateOfLeapFebruary);
}
