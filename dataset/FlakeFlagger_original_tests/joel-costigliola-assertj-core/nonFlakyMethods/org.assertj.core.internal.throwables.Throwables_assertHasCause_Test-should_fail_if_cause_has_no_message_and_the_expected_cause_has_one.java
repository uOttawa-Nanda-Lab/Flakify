@Test public void should_fail_if_cause_has_no_message_and_the_expected_cause_has_one(){
  AssertionInfo info=someInfo();
  Throwable expectedCause=new IllegalArgumentException("error cause");
  throwableWithCause=new Throwable(new IllegalArgumentException());
  try {
    throwables.assertHasCause(info,throwableWithCause,expectedCause);
  }
 catch (  AssertionError err) {
    verify(failures).failure(info,shouldHaveCause(throwableWithCause.getCause(),expectedCause));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
