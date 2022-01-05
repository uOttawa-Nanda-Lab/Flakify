@Test public void should_create_use_overriding_error_message_if_it_is_specified(){
  info.overridingErrorMessage("my message");
  AssertionError failure=failures.failure(info,errorMessage);
  assertEquals("my message",failure.getMessage());
}
