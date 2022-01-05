@Test public void should_fail_if_given_date_string_representation_cant_be_parsed_with_default_date_formats(){
  final String dateAsString="2003/04/26";
  thrown.expectAssertionError("Failed to parse " + dateAsString + " with any of these date formats: ["+ "yyyy-MM-dd'T'HH:mm:ss.SSS, "+ "yyyy-MM-dd HH:mm:ss.SSS, "+ "yyyy-MM-dd'T'HH:mm:ss, "+ "yyyy-MM-dd]");
  assertThat(new Date()).isEqualTo(dateAsString);
}
