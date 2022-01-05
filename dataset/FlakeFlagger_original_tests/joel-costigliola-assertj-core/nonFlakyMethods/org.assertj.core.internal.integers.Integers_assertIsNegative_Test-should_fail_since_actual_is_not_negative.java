@Test public void should_fail_since_actual_is_not_negative(){
  thrown.expectAssertionError("Expecting:\n <6>\nto be less than:\n <0>");
  integers.assertIsNegative(someInfo(),6);
}
