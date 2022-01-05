@Test public void should_create_error_message_ignoring_hours() throws ParseException {
  ErrorMessageFactory factory=shouldBeEqual(parseDatetimeWithMs("2011-01-01T05:00:00.000"),parseDatetimeWithMs("2011-01-01T06:05:17.003"),TimeUnit.HOURS);
  String message=factory.create(new TextDescription("Test"),new StandardRepresentation());
  assertThat(message).isEqualTo("[Test] \nExpecting:\n  <2011-01-01T05:00:00>\n" + "to have same year, month and day as:\n  " + "<2011-01-01T06:05:17>\n"+ "but had not.");
}
