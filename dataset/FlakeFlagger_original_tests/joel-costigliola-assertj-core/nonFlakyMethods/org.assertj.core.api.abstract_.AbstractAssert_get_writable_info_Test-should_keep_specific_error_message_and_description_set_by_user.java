@Test public void should_keep_specific_error_message_and_description_set_by_user(){
  try {
    new ConcreteAssert(6L).as("user description").checkNull();
  }
 catch (  AssertionError e) {
    assertEquals("[user description] specific error message",e.getMessage());
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
