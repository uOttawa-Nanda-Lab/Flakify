@Test public void should_parse_string_with_date_time_format(){
  Date date=parseDatetime("1994-08-26T00:00:00");
  assertEquals("1994-08-26T00:00:00",formatAsDatetime(date));
}
