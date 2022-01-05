@Test public void should_fail_since_actual_is_not_negative(){
  thrown.expectAssertionError("\nExpecting:\n <1>\nto be less than:\n <0>");
  bigDecimals.assertIsNegative(someInfo(),BigDecimal.ONE);
}
