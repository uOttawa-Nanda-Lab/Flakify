@Test public void should_fail_since_actual_is_not_negative(){
  thrown.expectAssertionError("\nExpecting:\n <6>\nto be less than:\n <0>");
  bytes.assertIsNegative(someInfo(),(byte)0x06);
}
