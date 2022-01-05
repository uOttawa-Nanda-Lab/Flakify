@Test public void should_throw_error_if_Index_is_out_of_bounds_whatever_custom_comparison_strategy_is(){
  thrown.expectIndexOutOfBoundsException("Index should be between <0> and <2> (inclusive,) but was:\n <6>");
  arraysWithCustomComparisonStrategy.assertContains(someInfo(),actual,-8f,atIndex(6));
}
