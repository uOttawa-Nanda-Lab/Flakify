@Test public void should_fail_since_actual_is_not_zero_whatever_custom_comparison_strategy_is_in_hex_representation(){
  try {
    bytesWithAbsValueComparisonStrategy.assertIsZero(someHexInfo(),(byte)0x01);
  }
 catch (  AssertionError e) {
    assertEquals("expected:<0x0[0]> but was:<0x0[1]>",e.getMessage());
  }
}
