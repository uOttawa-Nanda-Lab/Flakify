@Test public void should_verify_assertion_with_date_arg_string_with_default_format() throws ParseException {
  assertionInvocationWithStringArg(dateAsStringWithDefaultFormat);
  verifyAssertionInvocation(parse(dateAsStringWithDefaultFormat));
}
