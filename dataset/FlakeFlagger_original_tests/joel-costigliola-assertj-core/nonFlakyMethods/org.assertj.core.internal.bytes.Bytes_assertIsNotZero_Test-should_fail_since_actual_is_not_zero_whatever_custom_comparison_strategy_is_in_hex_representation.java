@Test public void should_fail_since_actual_is_not_zero_whatever_custom_comparison_strategy_is_in_hex_representation(){
  try {
    bytesWithAbsValueComparisonStrategy.assertIsNotZero(someHexInfo(),(byte)0x00);
  }
 catch (  AssertionError e) {
    assertEquals(e.getMessage(),"\nExpecting:\n <0x00>\nnot to be equal to:\n <0x00>\n");
  }
}
