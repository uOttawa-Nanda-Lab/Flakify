@Test public void should_fail_since_actual_is_positive_according_to_custom_comparison_strategy(){
  thrown.expectAssertionError("\nExpecting:\n <1>\nto be less than or equal to:\n <0> when comparing values using 'AbsValueComparator'");
  bigDecimalsWithAbsValueComparisonStrategy.assertIsNotPositive(someInfo(),BigDecimal.ONE);
}
