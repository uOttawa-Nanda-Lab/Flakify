@Test public void should_verify_assertion_with_date_arg_string_following_custom_format() throws ParseException {
  assertions.withDateFormat(customDateFormat);
  assertionInvocationWithStringArg(dateAsStringWithCustomFormat);
  verifyAssertionInvocation(parse(dateAsStringWithCustomFormat));
  assertions.withDefaultDateFormatsOnly();
}
