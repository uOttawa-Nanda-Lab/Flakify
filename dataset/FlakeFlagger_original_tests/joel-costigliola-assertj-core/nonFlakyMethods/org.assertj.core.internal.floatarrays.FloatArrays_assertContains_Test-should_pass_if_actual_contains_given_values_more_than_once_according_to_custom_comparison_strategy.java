@Test public void should_pass_if_actual_contains_given_values_more_than_once_according_to_custom_comparison_strategy(){
  actual=arrayOf(6f,-8f,10f,10f,-8f);
  arraysWithCustomComparisonStrategy.assertContains(someInfo(),actual,arrayOf(-8f));
}
