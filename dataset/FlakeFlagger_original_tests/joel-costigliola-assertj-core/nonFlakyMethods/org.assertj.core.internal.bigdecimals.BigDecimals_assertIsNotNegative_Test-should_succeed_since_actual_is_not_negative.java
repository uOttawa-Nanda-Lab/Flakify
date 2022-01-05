@Test public void should_succeed_since_actual_is_not_negative(){
  bigDecimals.assertIsNotNegative(someInfo(),new BigDecimal(6));
}
