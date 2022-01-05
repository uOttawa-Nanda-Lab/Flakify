@Test public void test1stSunday(){
  int expectedDateOfFirstSunOfJuly2010=4;
  Calendar july2010=new GregorianCalendar();
  july2010.set(Calendar.MONTH,Calendar.JULY);
  july2010.set(Calendar.YEAR,2010);
  int dateOfFirstSunOfJuly2010=CalendarUtil.getNthDayOfMonth(july2010,1,Calendar.SUNDAY);
  Assert.assertEquals("Unexpected date for 1st sunday of July 2010",expectedDateOfFirstSunOfJuly2010,dateOfFirstSunOfJuly2010);
}
