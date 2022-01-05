@Test public void should_fail_since_actual_is_zero(){
  thrown.expectAssertionError("\nExpecting:\n <0>\nto be less than:\n <0>");
  bigDecimals.assertIsNegative(someInfo(),BigDecimal.ZERO);
}
