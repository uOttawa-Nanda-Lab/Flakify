@Test public void should_fail_since_actual_is_not_negative_according_to_custom_comparison_strategy(){
  thrown.expectAssertionError("\nExpecting:\n <1>\nto be less than:\n <0>");
  shortsWithAbsValueComparisonStrategy.assertIsNegative(someInfo(),(short)1);
}
