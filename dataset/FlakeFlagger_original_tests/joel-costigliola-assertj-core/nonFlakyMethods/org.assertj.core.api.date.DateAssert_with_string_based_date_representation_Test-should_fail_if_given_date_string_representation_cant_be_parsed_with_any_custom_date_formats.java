@Test public void should_fail_if_given_date_string_representation_cant_be_parsed_with_any_custom_date_formats(){
  thrown.expectAssertionError("Failed to parse 2003 04 26 with any of these date formats: " + "[yyyy/MM/dd'T'HH:mm:ss, yyyy/MM/dd, yyyy-MM-dd'T'HH:mm:ss.SSS, yyyy-MM-dd HH:mm:ss.SSS, yyyy-MM-dd'T'HH:mm:ss, yyyy-MM-dd]");
  final Date date=Dates.parse("2003-04-26");
  registerCustomDateFormat("yyyy/MM/dd'T'HH:mm:ss");
  registerCustomDateFormat("yyyy/MM/dd'T'HH:mm:ss");
  assertThat(date).withDateFormat("yyyy/MM/dd").isEqualTo("2003 04 26");
}
