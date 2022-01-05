@Test public void should_pass_if_actual_is_in_range(){
  bigDecimals.assertIsBetween(someInfo(),ONE,ZERO,TEN);
  bigDecimals.assertIsBetween(someInfo(),ONE,ONE,TEN);
  bigDecimals.assertIsBetween(someInfo(),ONE,new BigDecimal("1.00"),TEN);
  bigDecimals.assertIsBetween(someInfo(),ONE,ZERO,new BigDecimal("1.00"));
}
