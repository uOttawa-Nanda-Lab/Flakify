@Test public void should_pass_if_actual_contains_all_given_values_according_to_custom_comparison_strategy(){
  arraysWithCustomComparisonStrategy.assertContains(someInfo(),actual,arrayOf(6L,-8L,10L));
}
