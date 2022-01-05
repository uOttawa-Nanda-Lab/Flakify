@Test public void should_fail_since_actual_is_positive(){
  thrown.expectAssertionError("Expecting:\n <6L>\nto be less than or equal to:\n <0L>");
  longs.assertIsNotPositive(someInfo(),6L);
}
