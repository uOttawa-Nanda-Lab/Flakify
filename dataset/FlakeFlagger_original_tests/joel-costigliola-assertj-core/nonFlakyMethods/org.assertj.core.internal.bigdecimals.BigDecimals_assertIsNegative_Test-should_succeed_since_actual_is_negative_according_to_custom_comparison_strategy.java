@Test public void should_succeed_since_actual_is_negative_according_to_custom_comparison_strategy(){
  bigDecimalsWithComparatorComparisonStrategy.assertIsNegative(someInfo(),new BigDecimal("-1.0"));
}
