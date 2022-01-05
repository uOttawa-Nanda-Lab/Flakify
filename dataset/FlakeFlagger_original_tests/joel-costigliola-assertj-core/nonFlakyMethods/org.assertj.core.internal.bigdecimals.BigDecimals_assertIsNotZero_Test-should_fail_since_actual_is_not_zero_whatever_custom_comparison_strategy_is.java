@Test public void should_fail_since_actual_is_not_zero_whatever_custom_comparison_strategy_is(){
  try {
    bigDecimalsWithComparatorComparisonStrategy.assertIsNotZero(someInfo(),BigDecimal.ZERO);
  }
 catch (  AssertionError e) {
    assertEquals(e.getMessage(),"\nExpecting:\n <0>\nnot to be equal to:\n <0>\n");
  }
}
