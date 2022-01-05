@Test public void should_fail_since_actual_is_zero_whatever_custom_comparison_strategy_is(){
  try {
    floatsWithAbsValueComparisonStrategy.assertIsNotZero(someInfo(),0.0f);
  }
 catch (  AssertionError e) {
    assertEquals(e.getMessage(),"\nExpecting:\n <0.0f>\nnot to be equal to:\n <0.0f>\n");
  }
}
