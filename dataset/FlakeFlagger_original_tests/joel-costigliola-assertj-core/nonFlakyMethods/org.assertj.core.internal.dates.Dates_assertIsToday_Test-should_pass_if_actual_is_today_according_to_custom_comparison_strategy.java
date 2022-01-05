@Test public void should_pass_if_actual_is_today_according_to_custom_comparison_strategy(){
  actual=monthOf(tomorrow()) == monthOf(new Date()) ? tomorrow() : yesterday();
  datesWithCustomComparisonStrategy.assertIsToday(someInfo(),actual);
}
