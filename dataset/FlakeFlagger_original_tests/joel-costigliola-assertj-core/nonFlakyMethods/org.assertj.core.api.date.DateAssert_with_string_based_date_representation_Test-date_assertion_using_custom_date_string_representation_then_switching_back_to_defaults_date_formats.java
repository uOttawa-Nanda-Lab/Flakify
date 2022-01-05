@Test public void date_assertion_using_custom_date_string_representation_then_switching_back_to_defaults_date_formats(){
  final Date date=Dates.parse("2003-04-26");
  assertThat(date).withDateFormat("yyyy/MM/dd").isEqualTo("2003/04/26").withDefaultDateFormatsOnly().isEqualTo("2003-04-26");
  assertThat(date).withDateFormat("yyyy/MM/dd").isEqualTo("2003/04/26");
  assertThat(date).withDefaultDateFormatsOnly().isEqualTo("2003-04-26");
}
