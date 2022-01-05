@Test public void should_fail_if_actual_has_no_cause_and_expected_cause_is_not_null(){
  AssertionInfo info=someInfo();
  Throwable expectedCause=new Throwable();
  try {
    throwables.assertHasCause(info,actual,expectedCause);
  }
 catch (  AssertionError err) {
    verify(failures).failure(info,shouldHaveCause(actual.getCause(),expectedCause));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
