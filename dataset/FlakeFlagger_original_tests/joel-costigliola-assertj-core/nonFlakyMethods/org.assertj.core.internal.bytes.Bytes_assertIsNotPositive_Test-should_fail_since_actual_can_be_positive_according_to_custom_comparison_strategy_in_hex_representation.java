@Test public void should_fail_since_actual_can_be_positive_according_to_custom_comparison_strategy_in_hex_representation(){
  thrown.expectAssertionError("\nExpecting:\n <0xFF>\nto be less than or equal to:\n <0x00> when comparing values using 'AbsValueComparator'");
  bytesWithAbsValueComparisonStrategy.assertIsNotPositive(someHexInfo(),(byte)0xFF);
}
