@Test public void should_pass_if_big_decimals_are_not_equal_according_to_custom_comparison_strategy(){
  bigDecimalsWithComparatorComparisonStrategy.assertNotEqual(someInfo(),TEN,ONE);
}
