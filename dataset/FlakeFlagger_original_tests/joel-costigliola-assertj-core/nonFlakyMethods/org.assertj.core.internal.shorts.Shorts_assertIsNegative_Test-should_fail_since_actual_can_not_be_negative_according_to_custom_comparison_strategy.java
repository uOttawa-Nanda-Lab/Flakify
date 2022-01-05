@Test public void should_fail_since_actual_can_not_be_negative_according_to_custom_comparison_strategy(){
  thrown.expectAssertionError("\nExpecting:\n <-1>\nto be less than:\n <0> when comparing values using 'AbsValueComparator'");
  shortsWithAbsValueComparisonStrategy.assertIsNegative(someInfo(),(short)-1);
}
