@Test public void should_return_minute_of_date() throws ParseException {
  String dateAsString="26/08/1994T22:35:00";
  Date date=new SimpleDateFormat("dd/MM/yyyy'T'hh:mm:ss").parse(dateAsString);
  assertEquals(35,minuteOf(date));
}
