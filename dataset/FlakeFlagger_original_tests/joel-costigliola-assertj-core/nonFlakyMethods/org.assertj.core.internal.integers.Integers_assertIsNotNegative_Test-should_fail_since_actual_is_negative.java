@Test public void should_fail_since_actual_is_negative(){
  thrown.expectAssertionError("\nExpecting:\n <-6>\nto be greater than or equal to:\n <0>");
  integers.assertIsNotNegative(someInfo(),-6);
}
