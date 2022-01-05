/** 
 * Asserts timeouts based on next day of week. Uses expression dayOfWeek=saturday hour=3 minute=21 second=50. Expected next timeout is SAT 2014-03-29 3:21:50
 */
@Test public void testNextDayOfWeek(){
  testNextDayOfWeek(new GregorianCalendar(2014,2,22,4,0,0).getTime());
  testNextDayOfWeek(new GregorianCalendar(2014,2,25,2,0,0).getTime());
}
