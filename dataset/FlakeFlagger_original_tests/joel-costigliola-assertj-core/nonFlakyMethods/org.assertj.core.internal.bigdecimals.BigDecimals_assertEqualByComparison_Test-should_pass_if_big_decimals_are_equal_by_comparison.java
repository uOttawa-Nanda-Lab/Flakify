@Test public void should_pass_if_big_decimals_are_equal_by_comparison(){
  bigDecimals.assertEqualByComparison(someInfo(),new BigDecimal("5.0"),new BigDecimal("5.00"));
}
