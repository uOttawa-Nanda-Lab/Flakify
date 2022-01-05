@Test public void should_fail_if_actual_matches_Pattern_whatever_custom_comparison_strategy_is(){
  AssertionInfo info=someInfo();
  Pattern pattern=matchAnything();
  try {
    stringsWithCaseInsensitiveComparisonStrategy.assertDoesNotMatch(info,actual,pattern);
  }
 catch (  AssertionError e) {
    verify(failures).failure(info,shouldNotMatch(actual,pattern.pattern()));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
