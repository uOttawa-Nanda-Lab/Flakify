@Test public void should_use_ErrorMessage_when_overriding_error_message_is_not_specified(){
  Description description=new TestDescription("description");
  info.description(description);
  when(errorMessage.create(description,info.representation())).thenReturn("[description] my message");
  AssertionError failure=failures.failure(info,errorMessage);
  assertEquals("[description] my message",failure.getMessage());
}
