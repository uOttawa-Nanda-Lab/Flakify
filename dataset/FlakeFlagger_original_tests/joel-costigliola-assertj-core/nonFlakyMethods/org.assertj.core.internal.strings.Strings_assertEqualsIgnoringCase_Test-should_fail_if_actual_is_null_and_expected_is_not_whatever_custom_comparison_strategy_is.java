@Test public void should_fail_if_actual_is_null_and_expected_is_not_whatever_custom_comparison_strategy_is(){
  AssertionInfo info=someInfo();
  try {
    stringsWithCaseInsensitiveComparisonStrategy.assertEqualsIgnoringCase(info,null,"Luke");
  }
 catch (  AssertionError e) {
    verifyFailureThrownWhenStringsAreNotEqual(info,null,"Luke");
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
