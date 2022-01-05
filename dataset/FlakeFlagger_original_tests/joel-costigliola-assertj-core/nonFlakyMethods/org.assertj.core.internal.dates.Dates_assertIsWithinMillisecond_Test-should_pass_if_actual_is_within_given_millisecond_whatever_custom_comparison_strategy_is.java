@Test public void should_pass_if_actual_is_within_given_millisecond_whatever_custom_comparison_strategy_is(){
  datesWithCustomComparisonStrategy.assertIsWithinMillisecond(someInfo(),actual,13);
}
