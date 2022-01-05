@Test public void should_fail_with_overridden_error_message_not_interpreted_with_string_format_feature_as_no_args_are_given(){
  try {
    assertions.overridingErrorMessage("new error message with special character like (%)").isEqualTo(8L);
  }
 catch (  AssertionError err) {
    assertEquals("new error message with special character like (%)",err.getMessage());
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
