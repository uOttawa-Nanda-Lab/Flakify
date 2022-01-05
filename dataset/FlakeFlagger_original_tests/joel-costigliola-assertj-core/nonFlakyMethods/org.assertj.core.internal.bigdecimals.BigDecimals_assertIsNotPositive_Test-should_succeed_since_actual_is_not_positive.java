@Test public void should_succeed_since_actual_is_not_positive(){
  bigDecimals.assertIsNotPositive(someInfo(),new BigDecimal(-6));
}
