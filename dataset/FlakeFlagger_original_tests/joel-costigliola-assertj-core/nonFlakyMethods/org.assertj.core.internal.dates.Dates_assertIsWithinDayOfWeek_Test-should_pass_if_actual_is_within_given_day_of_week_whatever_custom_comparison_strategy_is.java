@Test public void should_pass_if_actual_is_within_given_day_of_week_whatever_custom_comparison_strategy_is(){
  datesWithCustomComparisonStrategy.assertIsWithinDayOfWeek(someInfo(),actual,SATURDAY);
}
