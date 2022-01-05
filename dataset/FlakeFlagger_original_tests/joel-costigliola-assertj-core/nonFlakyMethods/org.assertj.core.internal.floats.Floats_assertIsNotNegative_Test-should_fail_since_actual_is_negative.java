@Test public void should_fail_since_actual_is_negative(){
  thrown.expectAssertionError("\nExpecting:\n <-6.0f>\nto be greater than or equal to:\n <0.0f>");
  floats.assertIsNotNegative(someInfo(),-6f);
}
