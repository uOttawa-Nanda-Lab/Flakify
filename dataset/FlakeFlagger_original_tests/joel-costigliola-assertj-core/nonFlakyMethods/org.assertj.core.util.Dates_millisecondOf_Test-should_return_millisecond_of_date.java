@Test public void should_return_millisecond_of_date() throws ParseException {
  String dateAsString="26/08/1994T22:35:17:29";
  Date date=new SimpleDateFormat("dd/MM/yyyy'T'hh:mm:ss:SS").parse(dateAsString);
  assertEquals(29,millisecondOf(date));
}
