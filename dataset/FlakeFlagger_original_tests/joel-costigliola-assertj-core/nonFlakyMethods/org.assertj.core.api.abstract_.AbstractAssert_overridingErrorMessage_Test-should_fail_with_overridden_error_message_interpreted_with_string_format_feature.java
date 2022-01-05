@Test public void should_fail_with_overridden_error_message_interpreted_with_string_format_feature(){
  try {
    long expected=8L;
    assertions.overridingErrorMessage("new error message, expected value was : '%s'",expected).isEqualTo(expected);
  }
 catch (  AssertionError err) {
    assertEquals("new error message, expected value was : '8'",err.getMessage());
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
