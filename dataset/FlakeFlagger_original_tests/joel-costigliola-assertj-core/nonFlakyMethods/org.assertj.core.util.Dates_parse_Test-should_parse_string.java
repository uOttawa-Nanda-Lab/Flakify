@Test public void should_parse_string() throws ParseException {
  String dateAsString="26/08/1994";
  SimpleDateFormat formatter=new SimpleDateFormat("dd/MM/yyyy");
  assertEquals(formatter.parse(dateAsString),parse("1994-08-26"));
}
