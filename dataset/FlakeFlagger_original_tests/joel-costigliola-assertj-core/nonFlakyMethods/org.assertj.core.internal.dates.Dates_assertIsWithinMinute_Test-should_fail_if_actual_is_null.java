@Test public void should_fail_if_actual_is_null(){
  thrown.expectAssertionError(actualIsNull());
  dates.assertIsWithinMinute(someInfo(),null,49);
}
