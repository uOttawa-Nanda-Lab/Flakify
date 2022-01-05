@Test public void should_fail_if_both_Strings_are_not_equal_regardless_of_case_whatever_custom_comparison_strategy_is(){
  AssertionInfo info=someInfo();
  try {
    stringsWithCaseInsensitiveComparisonStrategy.assertEqualsIgnoringCase(info,"Yoda","Luke");
  }
 catch (  AssertionError e) {
    verifyFailureThrownWhenStringsAreNotEqual(info,"Yoda","Luke");
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
