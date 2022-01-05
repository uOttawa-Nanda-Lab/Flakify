@Test public void should_create_error_message() throws ParseException {
  ErrorMessageFactory factory=shouldBeInSameSecondWindow(parseDatetimeWithMs("2011-01-01T05:00:01.000"),parseDatetimeWithMs("2011-01-01T05:00:02.001"));
  String message=factory.create(new TextDescription("Test"),new StandardRepresentation());
  assertThat(message).isEqualTo("[Test] \nExpecting:\n  <2011-01-01T05:00:01>\nto be close to:\n  " + "<2011-01-01T05:00:02>\nby less than one second (strictly) but difference was: 1s" + " and 1ms");
}
