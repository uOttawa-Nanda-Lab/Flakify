@Test public void should_fail_since_actual_is_not_negative(){
  thrown.expectAssertionError("\nExpecting:\n <6.0>\nto be less than:\n <0.0>");
  doubles.assertIsNegative(someInfo(),6.0d);
}
