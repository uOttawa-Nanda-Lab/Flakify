@Test public void should_succeed_since_actual_is_negative(){
  bigDecimals.assertIsNegative(someInfo(),new BigDecimal("-1.0"));
}
