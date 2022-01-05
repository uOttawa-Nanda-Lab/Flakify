@Test public void should_fail_if_actual_is_null(){
  thrown.expectAssertionError(actualIsNull());
  shorts.assertGreaterThanOrEqualTo(someInfo(),null,(short)8);
}
