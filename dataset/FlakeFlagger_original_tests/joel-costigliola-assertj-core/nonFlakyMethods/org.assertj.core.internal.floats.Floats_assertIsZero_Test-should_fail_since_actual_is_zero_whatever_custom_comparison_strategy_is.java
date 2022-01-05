@Test public void should_fail_since_actual_is_zero_whatever_custom_comparison_strategy_is(){
  try {
    floatsWithAbsValueComparisonStrategy.assertIsZero(someInfo(),2.0f);
  }
 catch (  AssertionError e) {
    assertEquals(e.getMessage(),"expected:<[0].0f> but was:<[2].0f>");
  }
}
