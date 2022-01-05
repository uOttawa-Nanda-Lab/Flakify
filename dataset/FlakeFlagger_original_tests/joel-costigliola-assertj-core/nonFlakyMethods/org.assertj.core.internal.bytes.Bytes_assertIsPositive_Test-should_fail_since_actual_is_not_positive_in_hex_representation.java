@Test public void should_fail_since_actual_is_not_positive_in_hex_representation(){
  thrown.expectAssertionError("\nExpecting:\n <0xFA>\nto be greater than:\n <0x00>");
  bytes.assertIsPositive(someHexInfo(),(byte)0xFA);
}
