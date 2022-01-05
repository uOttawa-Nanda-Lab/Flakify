@Test public void should_fail_if_cause_is_not_instance_of_expected_type(){
  AssertionInfo info=someInfo();
  Throwable expectedCause=new NullPointerException(EXCEPTION_MESSAGE);
  try {
    throwables.assertHasCause(info,throwableWithCause,expectedCause);
  }
 catch (  AssertionError err) {
    verify(failures).failure(info,shouldHaveCause(throwableWithCause.getCause(),expectedCause));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
