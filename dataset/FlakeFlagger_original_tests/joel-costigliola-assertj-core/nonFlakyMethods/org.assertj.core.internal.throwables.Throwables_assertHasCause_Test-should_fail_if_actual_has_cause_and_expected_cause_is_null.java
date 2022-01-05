@Test public void should_fail_if_actual_has_cause_and_expected_cause_is_null(){
  AssertionInfo info=someInfo();
  try {
    throwables.assertHasCause(info,throwableWithCause,null);
  }
 catch (  AssertionError err) {
    verify(failures).failure(info,shouldHaveNoCause(throwableWithCause));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
