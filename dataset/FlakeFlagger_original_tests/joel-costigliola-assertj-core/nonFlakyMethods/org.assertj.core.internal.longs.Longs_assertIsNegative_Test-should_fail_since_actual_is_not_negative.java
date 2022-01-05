@Test public void should_fail_since_actual_is_not_negative(){
  thrown.expectAssertionError("Expecting:\n <6L>\nto be less than:\n <0L>");
  longs.assertIsNegative(someInfo(),6l);
}
