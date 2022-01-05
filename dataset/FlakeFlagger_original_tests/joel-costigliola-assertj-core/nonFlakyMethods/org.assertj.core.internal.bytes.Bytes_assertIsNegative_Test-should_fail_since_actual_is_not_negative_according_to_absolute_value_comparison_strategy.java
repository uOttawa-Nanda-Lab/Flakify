@Test public void should_fail_since_actual_is_not_negative_according_to_absolute_value_comparison_strategy(){
  thrown.expectAssertionError("\nExpecting:\n <-6>\nto be less than:\n <0> when comparing values using 'AbsValueComparator'");
  bytesWithAbsValueComparisonStrategy.assertIsNegative(someInfo(),(byte)-6);
}
