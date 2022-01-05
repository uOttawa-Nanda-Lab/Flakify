@Test public void should_fail_if_actual_matches_regular_expression_whatever_custom_comparison_strategy_is(){
  AssertionInfo info=someInfo();
  String regex=matchAnything().pattern();
  try {
    stringsWithCaseInsensitiveComparisonStrategy.assertDoesNotMatch(info,actual,regex);
  }
 catch (  AssertionError e) {
    verify(failures).failure(info,shouldNotMatch(actual,regex));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
