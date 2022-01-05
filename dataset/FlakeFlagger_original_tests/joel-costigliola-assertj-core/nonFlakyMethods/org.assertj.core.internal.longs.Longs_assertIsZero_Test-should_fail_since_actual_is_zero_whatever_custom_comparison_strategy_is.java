@Test public void should_fail_since_actual_is_zero_whatever_custom_comparison_strategy_is(){
  try {
    longsWithAbsValueComparisonStrategy.assertIsNotZero(someInfo(),0L);
  }
 catch (  AssertionError e) {
    assertEquals(e.getMessage(),"\nExpecting:\n <0L>\nnot to be equal to:\n <0L>\n");
  }
}
