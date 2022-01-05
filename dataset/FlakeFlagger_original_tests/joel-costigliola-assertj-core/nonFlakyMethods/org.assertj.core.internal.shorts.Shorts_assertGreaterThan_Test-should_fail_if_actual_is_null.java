@Test public void should_fail_if_actual_is_null(){
  thrown.expectAssertionError(actualIsNull());
  shorts.assertGreaterThan(someInfo(),null,(short)8);
}
