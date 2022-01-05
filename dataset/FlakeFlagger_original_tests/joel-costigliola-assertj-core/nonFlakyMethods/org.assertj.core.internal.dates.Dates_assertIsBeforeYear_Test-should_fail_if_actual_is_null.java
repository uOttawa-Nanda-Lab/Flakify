@Test public void should_fail_if_actual_is_null(){
  thrown.expectAssertionError(actualIsNull());
  dates.assertIsBeforeYear(someInfo(),null,2010);
}
