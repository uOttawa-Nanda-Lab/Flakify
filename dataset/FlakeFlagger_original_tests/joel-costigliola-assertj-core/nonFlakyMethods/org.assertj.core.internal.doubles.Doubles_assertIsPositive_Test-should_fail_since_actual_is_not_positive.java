@Test public void should_fail_since_actual_is_not_positive(){
  thrown.expectAssertionError("\nExpecting:\n <-6.0>\nto be greater than:\n <0.0>");
  doubles.assertIsPositive(someInfo(),-6.0d);
}
