@Test public void should_fail_since_actual_is_not_zero_whatever_custom_comparison_strategy_is(){
  try {
    doublesWithAbsValueComparisonStrategy.assertIsZero(someInfo(),2.0d);
  }
 catch (  AssertionError e) {
    assertEquals(e.getMessage(),"expected:<[0].0> but was:<[2].0>");
  }
}
