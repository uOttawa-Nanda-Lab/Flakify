@Test public void should_fail_since_actual_is_not_negative_with_hex_representation(){
  thrown.expectAssertionError("\nExpecting:\n <0x06>\nto be less than:\n <0x00>");
  bytes.assertIsNegative(someHexInfo(),(byte)0x06);
}
