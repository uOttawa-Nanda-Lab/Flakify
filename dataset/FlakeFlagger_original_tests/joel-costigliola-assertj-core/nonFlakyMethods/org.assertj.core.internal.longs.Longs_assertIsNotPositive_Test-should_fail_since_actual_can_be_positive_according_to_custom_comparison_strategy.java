@Test public void should_fail_since_actual_can_be_positive_according_to_custom_comparison_strategy(){
  thrown.expectAssertionError("Expecting:\n <-1L>\nto be less than or equal to:\n <0L> when comparing values using 'AbsValueComparator'");
  longsWithAbsValueComparisonStrategy.assertIsNotPositive(someInfo(),-1L);
}
