@Test public void should_fail_if_cause_has_not_the_expected_message(){
  AssertionInfo info=someInfo();
  Throwable expectedCause=new IllegalArgumentException(EXCEPTION_MESSAGE + "foo");
  try {
    throwables.assertHasCause(info,throwableWithCause,expectedCause);
  }
 catch (  AssertionError err) {
    verify(failures).failure(info,shouldHaveCause(throwableWithCause.getCause(),expectedCause));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
