@Test public void should_fail_because_date_string_representation_does_not_follow_expected_format(){
  thrown.expectAssertionError("Failed to parse " + dateAsStringWithBadFormat + " with any of these date formats: ["+ "yyyy-MM-dd'T'HH:mm:ss.SSS, "+ "yyyy-MM-dd HH:mm:ss.SSS, "+ "yyyy-MM-dd'T'HH:mm:ss, "+ "yyyy-MM-dd]");
  assertionInvocationWithStringArg(dateAsStringWithBadFormat);
}
