@Test public void should_fail_with_overridden_error_message(){
  try {
    assertions.overridingErrorMessage("new error message").isEqualTo(8L);
  }
 catch (  AssertionError err) {
    assertEquals("new error message",err.getMessage());
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
