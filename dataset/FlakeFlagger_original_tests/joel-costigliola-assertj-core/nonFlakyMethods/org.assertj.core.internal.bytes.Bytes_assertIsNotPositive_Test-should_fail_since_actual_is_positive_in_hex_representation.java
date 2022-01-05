@Test public void should_fail_since_actual_is_positive_in_hex_representation(){
  thrown.expectAssertionError("\nExpecting:\n <0x06>\nto be less than or equal to:\n <0x00>");
  bytes.assertIsNotPositive(someHexInfo(),(byte)0x06);
}
