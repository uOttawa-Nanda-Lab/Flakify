@Test public void should_fail_since_actual_is_not_positive_according_to_custom_comparison_strategy(){
  thrown.expectAssertionError("\nExpecting:\n <0L>\nto be greater than:\n <0L> when comparing values using 'AbsValueComparator'");
  longsWithAbsValueComparisonStrategy.assertIsPositive(someInfo(),0L);
}
