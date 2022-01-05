@Test public void should_fail_since_actual_can_be_positive_according_to_custom_comparison_strategy(){
  thrown.expectAssertionError("Expecting:\n <-1>\nto be less than or equal to:\n <0> when comparing values using 'AbsValueComparator'");
  shortsWithAbsValueComparisonStrategy.assertIsNotPositive(someInfo(),(short)-1);
}
