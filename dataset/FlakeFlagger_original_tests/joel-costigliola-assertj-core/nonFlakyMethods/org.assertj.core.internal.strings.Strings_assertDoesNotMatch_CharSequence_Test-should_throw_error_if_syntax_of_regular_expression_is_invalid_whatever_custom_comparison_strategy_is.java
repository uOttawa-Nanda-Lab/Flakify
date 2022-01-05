@Test public void should_throw_error_if_syntax_of_regular_expression_is_invalid_whatever_custom_comparison_strategy_is(){
  thrown.expect(PatternSyntaxException.class);
  stringsWithCaseInsensitiveComparisonStrategy.assertDoesNotMatch(someInfo(),actual,"*...");
}
