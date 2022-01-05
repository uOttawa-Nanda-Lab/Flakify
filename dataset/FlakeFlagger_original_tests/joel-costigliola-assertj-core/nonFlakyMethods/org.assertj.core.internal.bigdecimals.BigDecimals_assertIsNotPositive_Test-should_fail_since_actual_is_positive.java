@Test public void should_fail_since_actual_is_positive(){
  thrown.expectAssertionError("\nExpecting:\n <6>\nto be less than or equal to:\n <0>");
  bigDecimals.assertIsNotPositive(someInfo(),new BigDecimal(6));
}
