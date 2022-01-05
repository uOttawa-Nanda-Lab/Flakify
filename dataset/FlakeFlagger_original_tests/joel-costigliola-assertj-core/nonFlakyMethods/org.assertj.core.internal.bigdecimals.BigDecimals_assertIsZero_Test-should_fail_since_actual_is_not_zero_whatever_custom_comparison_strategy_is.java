@Test public void should_fail_since_actual_is_not_zero_whatever_custom_comparison_strategy_is(){
  try {
    bigDecimalsWithComparatorComparisonStrategy.assertIsZero(someInfo(),BigDecimal.ONE);
  }
 catch (  AssertionError e) {
    assertEquals(e.getMessage(),"expected:<[0]> but was:<[1]>");
  }
}
