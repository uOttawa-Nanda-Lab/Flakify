@Test public void should_fail_if_actual_is_null_whatever_custom_comparison_strategy_is(){
  thrown.expectAssertionError(actualIsNull());
  datesWithCustomComparisonStrategy.assertIsAfterOrEqualsTo(someInfo(),null,parseDate("2010-01-01"));
}
