@Test public void should_fail_if_actual_is_null(){
  thrown.expectAssertionError(actualIsNull());
  dates.assertIsBefore(someInfo(),null,parseDate("2010-01-01"));
}
