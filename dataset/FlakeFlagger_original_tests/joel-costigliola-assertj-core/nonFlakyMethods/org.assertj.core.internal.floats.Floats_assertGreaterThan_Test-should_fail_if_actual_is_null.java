@Test public void should_fail_if_actual_is_null(){
  thrown.expectAssertionError(actualIsNull());
  floats.assertGreaterThan(someInfo(),null,8f);
}
