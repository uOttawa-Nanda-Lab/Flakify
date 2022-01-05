@Test public void should_fail_if_actual_is_null_whatever_custom_comparison_strategy_is(){
  thrown.expectAssertionError(actualIsNull());
  datesWithCustomComparisonStrategy.assertIsInSameYearAs(someInfo(),null,new Date());
}
