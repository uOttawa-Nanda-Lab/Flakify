@Test public void should_return_toString_of_calendar(){
  GregorianCalendar calendar=new GregorianCalendar(2011,Calendar.JANUARY,18,23,53,17);
  assertEquals("2011-01-18T23:53:17",new StandardRepresentation().toStringOf(calendar));
}
