@Test public void use_custom_date_formats_set_from_Assertions_entry_point(){
  final Date date=Dates.parse("2003-04-26");
  registerCustomDateFormat("yyyy/MM/dd'T'HH:mm:ss");
  try {
    assertThat(date).isEqualTo("2003/04/26");
  }
 catch (  AssertionError e) {
    assertThat(e).hasMessage("Failed to parse 2003/04/26 with any of these date formats: " + "[yyyy/MM/dd'T'HH:mm:ss, yyyy-MM-dd'T'HH:mm:ss.SSS, yyyy-MM-dd HH:mm:ss.SSS, yyyy-MM-dd'T'HH:mm:ss, yyyy-MM-dd]");
  }
  registerCustomDateFormat("yyyy/MM/dd");
  assertThat(date).isEqualTo("2003/04/26");
  registerCustomDateFormat(new SimpleDateFormat("yyyy/MM/dd'T'HH:mm:ss.SSS"));
  assertThat(date).isEqualTo("2003/04/26T00:00:00.000");
  useDefaultDateFormatsOnly();
  assertThat(date).isEqualTo("2003-04-26");
  assertThat(date).isEqualTo("2003-04-26T00:00:00");
  assertThat(date).isEqualTo("2003-04-26T00:00:00.000");
}
