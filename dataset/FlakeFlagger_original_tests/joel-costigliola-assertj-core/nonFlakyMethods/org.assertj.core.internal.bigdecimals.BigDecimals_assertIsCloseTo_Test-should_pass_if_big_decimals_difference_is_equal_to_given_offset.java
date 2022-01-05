@Test public void should_pass_if_big_decimals_difference_is_equal_to_given_offset(){
  bigDecimals.assertIsCloseTo(someInfo(),new BigDecimal("5.0"),new BigDecimal("6.0"),offset(ONE));
}
