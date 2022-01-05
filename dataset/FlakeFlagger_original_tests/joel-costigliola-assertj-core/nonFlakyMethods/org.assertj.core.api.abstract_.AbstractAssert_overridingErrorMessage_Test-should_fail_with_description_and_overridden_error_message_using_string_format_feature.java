@Test public void should_fail_with_description_and_overridden_error_message_using_string_format_feature(){
  try {
    long expected=8L;
    assertions.as("test").overridingErrorMessage("new error message, expected value was : '%s'",expected).isEqualTo(expected);
  }
 catch (  AssertionError err) {
    assertEquals("[test] new error message, expected value was : '8'",err.getMessage());
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
