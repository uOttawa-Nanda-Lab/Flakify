@Test public void should_fail_since_actual_is_not_positive_according_to_custom_comparison_strategy_in_hex_representation(){
  thrown.expectAssertionError("\nExpecting:\n <0x00>\nto be greater than:\n <0x00> when comparing values using 'AbsValueComparator'");
  bytesWithAbsValueComparisonStrategy.assertIsPositive(someHexInfo(),(byte)0x00);
}
