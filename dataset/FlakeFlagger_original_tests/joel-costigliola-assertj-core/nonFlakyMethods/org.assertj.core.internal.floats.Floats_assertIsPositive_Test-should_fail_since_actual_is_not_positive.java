@Test public void should_fail_since_actual_is_not_positive(){
  thrown.expectAssertionError("\nExpecting:\n <-6.0f>\nto be greater than:\n <0.0f>");
  floats.assertIsPositive(someInfo(),-6.0f);
}
