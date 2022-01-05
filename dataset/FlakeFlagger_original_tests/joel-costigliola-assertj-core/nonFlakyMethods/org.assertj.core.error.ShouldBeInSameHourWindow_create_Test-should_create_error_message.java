@Test public void should_create_error_message() throws ParseException {
  ErrorMessageFactory factory=shouldBeInSameHourWindow(parseDatetimeWithMs("2011-01-01T05:00:00.000"),parseDatetimeWithMs("2011-01-01T06:05:17.003"));
  String message=factory.create(new TextDescription("Test"),new StandardRepresentation());
  assertThat(message).isEqualTo("[Test] \nExpecting:\n  <2011-01-01T05:00:00>\nto be close to:\n  " + "<2011-01-01T06:05:17>\n" + "by less than one hour (strictly) but difference was: 1h 5m 17s and 3ms");
}
