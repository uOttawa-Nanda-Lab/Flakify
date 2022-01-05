@Test public void should_throw_error_if_Pattern_is_null_whatever_custom_comparison_strategy_is(){
  thrown.expectNullPointerException(regexPatternIsNull());
  Pattern pattern=null;
  stringsWithCaseInsensitiveComparisonStrategy.assertMatches(someInfo(),actual,pattern);
}
