@Test public void should_fail_since_actual_is_negative_in_hex_representation(){
  thrown.expectAssertionError("\nExpecting:\n <0xFA>\nto be greater than or equal to:\n <0x00>");
  bytes.assertIsNotNegative(someHexInfo(),(byte)0xFA);
}
