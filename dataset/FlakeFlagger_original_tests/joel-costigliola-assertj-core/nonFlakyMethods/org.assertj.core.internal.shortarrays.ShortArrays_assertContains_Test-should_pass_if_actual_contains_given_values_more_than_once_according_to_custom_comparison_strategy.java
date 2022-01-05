@Test public void should_pass_if_actual_contains_given_values_more_than_once_according_to_custom_comparison_strategy(){
  actual=arrayOf(6,8,10,-10,8);
  arraysWithCustomComparisonStrategy.assertContains(someInfo(),actual,arrayOf(10));
}
