@Test public void should_convert_date_to_calendar() throws ParseException {
  String dateAsString="26/08/1994";
  Date date=new SimpleDateFormat("dd/MM/yyyy").parse(dateAsString);
  Calendar calendar=new GregorianCalendar();
  calendar.clear();
  calendar.set(1994,07,26);
  assertEquals(calendar,toCalendar(date));
}
