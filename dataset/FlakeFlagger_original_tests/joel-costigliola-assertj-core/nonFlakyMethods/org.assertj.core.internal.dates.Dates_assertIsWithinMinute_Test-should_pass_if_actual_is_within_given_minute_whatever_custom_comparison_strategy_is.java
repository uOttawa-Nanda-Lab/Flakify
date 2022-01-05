@Test public void should_pass_if_actual_is_within_given_minute_whatever_custom_comparison_strategy_is(){
  datesWithCustomComparisonStrategy.assertIsWithinMinute(someInfo(),actual,49);
}
