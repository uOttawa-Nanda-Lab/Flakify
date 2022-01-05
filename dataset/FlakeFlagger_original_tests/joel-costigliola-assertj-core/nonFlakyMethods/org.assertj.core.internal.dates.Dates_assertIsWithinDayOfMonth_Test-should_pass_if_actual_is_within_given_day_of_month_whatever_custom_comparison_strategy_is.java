@Test public void should_pass_if_actual_is_within_given_day_of_month_whatever_custom_comparison_strategy_is(){
  datesWithCustomComparisonStrategy.assertIsWithinDayOfMonth(someInfo(),actual,1);
}
