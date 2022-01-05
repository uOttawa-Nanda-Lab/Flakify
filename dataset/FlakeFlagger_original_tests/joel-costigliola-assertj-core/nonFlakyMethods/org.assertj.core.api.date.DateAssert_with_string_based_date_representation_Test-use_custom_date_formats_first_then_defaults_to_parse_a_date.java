@Test public void use_custom_date_formats_first_then_defaults_to_parse_a_date(){
  final Date date=Dates.parse("2003-04-26");
  assertThat(date).isEqualTo("2003-04-26");
  try {
    assertThat(date).isEqualTo("2003/04/26");
  }
 catch (  AssertionError e) {
    assertThat(e).hasMessage("Failed to parse 2003/04/26 with any of these date formats: [" + "yyyy-MM-dd'T'HH:mm:ss.SSS, " + "yyyy-MM-dd HH:mm:ss.SSS, "+ "yyyy-MM-dd'T'HH:mm:ss, "+ "yyyy-MM-dd]");
  }
  registerCustomDateFormat("yyyy/MM/dd");
  assertThat(date).isEqualTo("2003/04/26");
  assertThat(date).isEqualTo("2003-04-26");
  assertThat(date).isEqualTo("2003-04-26T00:00:00");
  try {
    assertThat(date).isEqualTo("2003 04 26");
  }
 catch (  AssertionError e) {
    assertThat(e).hasMessage("Failed to parse 2003 04 26 with any of these date formats: [" + "yyyy/MM/dd, " + "yyyy-MM-dd'T'HH:mm:ss.SSS, "+ "yyyy-MM-dd HH:mm:ss.SSS, "+ "yyyy-MM-dd'T'HH:mm:ss, "+ "yyyy-MM-dd]");
  }
  registerCustomDateFormat("yyyy MM dd");
  assertThat(date).isEqualTo("2003 04 26");
}
