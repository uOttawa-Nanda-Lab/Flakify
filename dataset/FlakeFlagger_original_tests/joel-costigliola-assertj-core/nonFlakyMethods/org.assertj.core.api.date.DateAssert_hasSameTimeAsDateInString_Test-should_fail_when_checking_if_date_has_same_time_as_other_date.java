@Test public void should_fail_when_checking_if_date_has_same_time_as_other_date() throws ParseException {
  Date date=parseDatetime("2003-04-26T12:00:00");
  thrown.expect(AssertionError.class);
  assertThat(date).hasSameTimeAs("2003-04-27T12:00:00");
}
