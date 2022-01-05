@Test public void should_fail_since_actual_is_not_zero_whatever_custom_comparison_strategy_is(){
  try {
    bytesWithAbsValueComparisonStrategy.assertIsZero(someInfo(),(byte)1);
  }
 catch (  AssertionError e) {
    assertEquals("expected:<[0]> but was:<[1]>",e.getMessage());
  }
}
