@Test public void should_fail_since_actual_is_positive(){
  thrown.expectAssertionError("Expecting:\n <6>\nto be less than or equal to:\n <0>");
  shorts.assertIsNotPositive(someInfo(),(short)6);
}
