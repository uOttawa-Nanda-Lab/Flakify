@Test public void should_fail_if_cause_has_different_type_and_message_to_expected_cause(){
  AssertionInfo info=someInfo();
  Throwable expectedCause=new NullPointerException("error cause");
  try {
    throwables.assertHasCause(info,throwableWithCause,expectedCause);
  }
 catch (  AssertionError err) {
    verify(failures).failure(info,shouldHaveCause(throwableWithCause.getCause(),expectedCause));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
