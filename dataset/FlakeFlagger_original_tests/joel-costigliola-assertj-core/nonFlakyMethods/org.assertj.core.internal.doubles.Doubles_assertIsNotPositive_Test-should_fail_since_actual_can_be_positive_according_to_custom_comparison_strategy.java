@Test public void should_fail_since_actual_can_be_positive_according_to_custom_comparison_strategy(){
  thrown.expectAssertionError("\nExpecting:\n <-1.0>\nto be less than or equal to:\n <0.0> when comparing values using 'AbsValueComparator'");
  doublesWithAbsValueComparisonStrategy.assertIsNotPositive(someInfo(),-1d);
}
